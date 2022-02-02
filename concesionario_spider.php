<?php
    // Control de errores
    ini_set('display_errors',1);
    error_reporting(E_ALL);
    
    require_once('settingsDB.php');
    require_once('DBConnection.php');
    
    // Conexión a la BBDD
    $dbc = new DBConnection($dbsettings);
    
    // Usuario e ID del Concensionario
    $usuario = 'Kia Asturconsa';
    $id = '54356';
    
    getUser($usuario,$id,$dbc);
    
    /**
     * Obtener la ficha del usuario pasándole el nombre y su id
     */
    function getUser($usuario,$id,$dbc) {
        $archivo = file_get_contents('https://www.milanuncios.com/tienda/'.strtolower(str_replace(' ','-',$usuario)).'-'.$id.'.htm');
            if ($archivo === false) {
                echo "Error: file_get_contents no ha podido acceder a la URL<br>";
                exit(1);
            }
        
        // Abrimos el fichero de escritura
        $file = "concesionario.txt";
        $fp = fopen($file,"w");
        fwrite($fp,$archivo);
        
        getAd($archivo,$dbc);
    }
    
    /**
     * Obtener las fichas de los anuncios de un usuario mediante un bucle 
     */
    function getAd($archivo,$dbc) {
        
        $findme   = '"@type": "Product",';
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $numAd = substr_count($archivo,$findme);
    
        for ($i = 0; $i < $numAd; $i++) {
            
            $findme   = '"@type": "Product",';
            $extra = strlen($findme);
            $posInicio = strpos($archivo, $findme) + $extra;
            
            $findme   = '"name":';
            $posFinal = strpos($archivo, $findme);
            
            $urlAd = substr($archivo, $posInicio, $posFinal-$posInicio);
            $urlAd = str_replace('"url": "','',$urlAd);
            $urlAd = str_replace('",','',$urlAd);
            
            writeAd(trim($urlAd),$dbc);
            
            $findme   = '"description"';
            $posFinal = strpos($archivo, $findme);
            $archivo = substr($archivo, $posFinal+1);
            
        }
    }
    
    /**
     * Obtener los datos de un anuncio a partir de la url de la web
     */
    function writeAd($ad,$dbc) {
        $archivo = file_get_contents($ad);
            if ($archivo === false) {
                echo "Error: file_get_contents no ha podido acceder a la URL<br>";
                exit(1);
            }
        
        // Abrimos el fichero de escritura
        $file = "concesionario.txt";
        $fp = fopen($file,"w");
        fwrite($fp,$archivo);
        
        echo "<br/><br/>-------------- ANUNCIO --------------<br/><br/>";
        
        // Cadenas delimitadoras para los datos de los anuncios
        $iniStrTitulo = 'Milanuncios - ';
        $finStrTitulo = '</title>';
        
        $iniStrDescripcion = "<p class=\"ma-AdDetail-description\">";
        $finStrDescripcion = "</p><a href=\"/";
        
        $iniStrReferencia = 'Ref:';
        
        $iniStrPrecio = '\"price\":{\"cashPrice\":{\"value\":';
        $finStrPrecio = ',\"text\":\"';
        
        $iniStrLocalizacion = '\"region_level2\":\"';
        $finStrLocalizacion = '\",\"search_terms\"';
        
        $iniStrImagenes = '\"urlp\":\"';
        $finStrImagenes = '?VersionId';
        
        $iniStrUrl = '<link data-rh="" rel="canonical" href="';
        $finStrUrl = '" data-reactroot=""/><link rel="stylesheet"';
        
        $iniStrCombustible = '\"fuel\",\"value\":\"';
        $finStrCombustible = '\"},{\"type\":\"hp';
        
        $iniStrKm = 'kilometers\",\"value\":';
        $finStrKm = '},{\"type\":\"transmission';
        
        $iniStrCambio = '\"transmission\":\"';
        $finStrCambio = '\",\"transmission_id\"';
        
        $iniStrColor = '"color\",\"value\":\"';
        $finStrColor = '\"},{\"type\":\"';
        
        $iniStrPotencia = 'hp\",\"value\":';
        $finStrPotencia = '},{\"type\":\"kilometers';
        
        $iniStrNPuertas = 'doors\",\"value\":';
        
        $iniStrAño = 'year\",\"value\":';
        $finStrAño = '}],\"legalAttributes';
        
        $titulo = getTitle($archivo, $iniStrTitulo, $finStrTitulo);
        $descripcion = getDescription($archivo, $iniStrDescripcion, $finStrDescripcion);
        $referencia = getReference($archivo, $iniStrReferencia);
        $precio = getPrice($archivo, $iniStrPrecio, $finStrPrecio);
        $localizacion = getLocation($archivo, $iniStrLocalizacion, $finStrLocalizacion);
        $totalImg = getImages($archivo, $iniStrImagenes, $finStrImagenes);
        $url = getLinkPage($archivo, $iniStrUrl, $finStrUrl);
        
        $combustible = getFuel($archivo, $iniStrCombustible, $finStrCombustible);
        $km = getKm($archivo, $iniStrKm, $finStrKm);
        $cambio = getTransmission($archivo, $iniStrCambio, $finStrCambio);
        $color = getColor($archivo, $iniStrColor, $finStrColor);
        $nPuertas = getNumDoors($archivo, $iniStrNPuertas);
        $potencia = getPower($archivo, $iniStrPotencia, $finStrPotencia);
        $año = getYear($archivo, $iniStrAño, $finStrAño);
        
        insertAd($dbc,$titulo,$descripcion,$referencia,$precio,$localizacion,$totalImg,$url,
                 $combustible,$km,$cambio,$color,$nPuertas,$potencia,$año);
        
        echo "<br/><br/>-------------------------------------<br/><br/>";
        
    }
    
    // titulo --------------
    function getTitle($archivo, $iniStrTitulo, $finStrTitulo) {
        $findme   = $iniStrTitulo;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrTitulo;
        $posFinal = strpos($archivo, $findme);
    
        $titulo = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Titulo: '.$titulo;
        echo "<br/><br/>";
        
        return $titulo;
    }
    
    // descripcion --------------
    function getDescription($archivo, $iniStrDescripcion, $finStrDescripcion) {
        $findme   = $iniStrDescripcion;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrDescripcion;
        $posFinal = strpos($archivo, $findme);
    
        $descripcion = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Descripcion: '.$descripcion;
        echo "<br/><br/>";
        
        return $descripcion;
    }
    
    // referencia --------------
    function getReference($archivo, $iniStrReferencia) {
        $findme   = $iniStrReferencia;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
    
        $referencia = substr($archivo, $posInicio, 10);
        
        //echo 'Referencia: '.$referencia;
        echo "<br/><br/>";
        
        return $referencia;
    }
    
    // precio --------------
    function getPrice($archivo, $iniStrPrecio, $finStrPrecio) {
        $findme   = $iniStrPrecio;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrPrecio;
        $posFinal = strpos($archivo, $findme);
    
        $precio = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Precio: '.$precio.' €';
        echo "<br/><br/>";
        
        return $precio;
    }
    
    // localizacion --------------
    function getLocation($archivo, $iniStrLocalizacion, $finStrLocalizacion) {
        $findme   = $iniStrLocalizacion;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrLocalizacion;
        $posFinal = strpos($archivo, $findme);
    
        $localizacion = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Localizacion: '.$localizacion;
        echo "<br/><br/>";
        
        return $localizacion;
    }
    
    // color --------------
    function getColor($archivo, $iniStrColor, $finStrColor) {
        $findme   = $iniStrColor;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrColor;
        $posFinal = strpos($archivo, $findme);
    
        $color = substr($archivo, $posInicio, $posFinal-$posInicio);
        $color = str_replace('\u00F3','ó',$color);
        
        //echo 'Color: '.$color;
        echo "<br/><br/>";
        
        return $color;
    }
    
    // nPuertas --------------
    function getNumDoors($archivo, $iniStrNPuertas) {
    $findme   = $iniStrNPuertas;
    $extra = strlen($findme);
    $posInicio = strpos($archivo, $findme) + $extra;

    $nPuertas = substr($archivo, $posInicio, 1);
    
    //echo 'Nº Puertas: '.$nPuertas;
    echo "<br/><br/>";
    
    return $nPuertas;
    }
    
    // Combustible --------------
    function getFuel($archivo, $iniStrCombustible, $finStrCombustible) {
    $findme   = $iniStrCombustible;
    $extra = strlen($findme);
    $posInicio = strpos($archivo, $findme) + $extra;
    
    $findme   = $finStrCombustible;
    $posFinal = strpos($archivo, $findme);

    $combustible = substr($archivo, $posInicio, $posFinal-$posInicio);
    
    //echo 'Combustible: '.$combustible;
    echo "<br/><br/>";
    
    return $combustible;
    }
    
    // Potencia --------------
    function getPower($archivo, $iniStrPotencia, $finStrPotencia) {
        $findme   = $iniStrPotencia;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrPotencia;
        $posFinal = strpos($archivo, $findme);
    
        $potencia = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Potencia: '.$potencia;
        echo "<br/><br/>";
        
        return $potencia;
    }
    
    // Km --------------
    function getKm($archivo, $iniStrKm, $finStrKm) {
        $findme   = $iniStrKm;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrKm;
        $posFinal = strpos($archivo, $findme);
    
        $km = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Km: '.$km;
        echo "<br/><br/>";
        
        return $km;
    }
    
    // Cambio --------------
    function getTransmission($archivo, $iniStrCambio, $finStrCambio) {
        $findme   = $iniStrCambio;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrCambio;
        $posFinal = strpos($archivo, $findme);
    
        $cambio = substr($archivo, $posInicio, $posFinal-$posInicio);
        $cambio = str_replace('\u00E1','á',$cambio);
        
        //echo 'Cambio: '.$cambio;
        echo "<br/><br/>";
        
        return $cambio;
    }
    
    // Año --------------
    function getYear($archivo, $iniStrAño, $finStrAño) {
        $findme   = $iniStrAño;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrAño;
        $posFinal = strpos($archivo, $findme);
    
        $año = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Año: '.$año;
        echo "<br/><br/>";
        
        return $año;
    }
    
    // Imagenes --------------
    function getImages($archivo, $iniStrImagenes, $finStrImagenes) {
        $findme   = $iniStrImagenes;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrImagenes;
        $posFinal = strpos($archivo, $findme);
    
        $imagenes = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        $findme   = '1 / ';
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
    
        $numImg = substr($archivo, $posInicio, 2);
        trim($numImg);
        
        $totalImg = array();
        
        for ($i = 1; $i <= $numImg; $i++) {
            $imagen = str_replace('_1.jpg', '_'.$i.'.jpg', $imagenes);
            //echo 'Imagen Nº '.$i.': '.$imagen;
            echo "<br/><br/>";
            
            $totalImg[$i] = $imagen;
        }
        
        return $totalImg;
    }
    
    // Url --------------
    function getLinkPage($archivo, $iniStrUrl, $finStrUrl) {
        $findme   = $iniStrUrl;
        $extra = strlen($findme);
        $posInicio = strpos($archivo, $findme) + $extra;
        
        $findme   = $finStrUrl;
        $posFinal = strpos($archivo, $findme);
    
        $url = substr($archivo, $posInicio, $posFinal-$posInicio);
        
        //echo 'Url: '.$url;
        echo "<br/><br/>";
        
        return $url;
    }
    
    /**
     * Borramos el contenido de la BD e insertamos los nuevos datos
     */
    function insertAd($dbc,$titulo,$descripcion,$referencia,$precio,$localizacion,$totalImg,$url,
                      $combustible,$km,$cambio,$color,$nPuertas,$potencia,$año) {
        
        $sql = "DELETE FROM coches";
        
        $afectedRows = $dbc -> runQuery($sql);
        
        $sql = "INSERT INTO coches 
                VALUES ( '".$titulo."', '"
                .$descripcion."', '"
                .$referencia."', '"
                .$precio."', '"
                .$localizacion."', '"
                .implode(';',$totalImg)."', '"
                .$url."', '"
                .$combustible."', '"
                .$km."', '"
                .$cambio."', '"
                .$color."', '"
                .$potencia."', '"
                .$nPuertas."', '"
                .$año.
                "' );";
        
        //echo $sql;
        //var_dump($totalImg);
        //exit;
        
        $afectedRows = $dbc -> runQuery($sql);
    }
    
?>