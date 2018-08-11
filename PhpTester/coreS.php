<?php

function submit()
{
    if (isset($_POST['btnSubmit']) && !empty($_POST['sMsg'])) {
        $id = getStudentID();
        $message = $_POST['sMsg'];
        $lang = $_POST['sLang'];


        $fileLoc = "sLog.txt";
        $file = fopen($fileLoc, 'a+');
                $entry = "You: " . $message . PHP_EOL;
                fwrite($file, $entry);
        fclose($file);



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

function getStudentID(){
    return "s12345678";
};

function getQuestion(){
    $fileLoc = "slog.txt";
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
