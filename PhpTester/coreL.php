<?php

function submit()
{
    if (isset($_POST['btnLSubmit']) && !empty($_POST['lMsg'])) {
        $lect = getLecture();
        $message = $_POST['lMsg'];
        $lang = "en";


        $fileLoc = "lLog.txt";
        $file = fopen($fileLoc, 'a+');
        $entry = "You: " . $message . PHP_EOL;
        fwrite($file, $entry);
        fclose($file);



        saveMessage("LECT", $message, $lang);

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
            // Q(0) OR A(1)
            $entry = "1" . PHP_EOL;
            fwrite($file, $entry);
            $entry = "0" . PHP_EOL; // isTranslated
            fwrite($file, $entry);

            fclose($file);
            $saved = true;
        }
        $count++;
    }
}

function getLecture(){
    return "80.10.12";
};

function getQuestion(){
    $fileLoc = "lLog.txt";
    $text = "";
    $file = fopen($fileLoc, 'a+');
    while (!feof($file)) {
        $data = fgets($file);
        if (!$data == '') {
            $text .=  "<p>" . $data . "</p><hr/>" ;
        }
    }
    fclose($file);


    return $text;
}
