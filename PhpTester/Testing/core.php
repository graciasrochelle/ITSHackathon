<?php
/**
 * Created by PhpStorm.
 * User: Dimmerworld
 * Date: 11/08/2018
 * Time: 8:13 PM
 */
function submit()
{
    if (isset($_POST['btnSubmit']) && !empty($_POST['msg'])) {
        $id = $_POST['sID'];
        $message = $_POST['msg'];
        $lang = $_POST['sLang'];
        saveMessage($id, $message, $lang);

}}

    function saveMessage($id, $message, $lang)
        {
            $count = 0;
            $saved = false;

            while(!$saved){
                $fileLoc = "data/message" . $count . ".txt";

                if(!file_exists($fileLoc)) {

                    $file = fopen($fileLoc, 'w');
                    $entry = $id . PHP_EOL;
                    fwrite($file, $entry);
                    $entry = $message . PHP_EOL;
                    fwrite($file, $entry);
                    $entry = "en" . PHP_EOL;
                    fwrite($file, $entry);
                    $entry = $lang . PHP_EOL;
                    fwrite($file, $entry);
                    $entry = "0" . PHP_EOL;
                    fwrite($file, $entry);
                    $entry = "0" . PHP_EOL;
                    fwrite($file, $entry);

                    fclose($file);
                    $saved = true;
                }
                $count++;
             }
        }