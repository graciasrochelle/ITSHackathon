<?php

function submit()
{
    if (isset($_POST['btnSubmit']) && !empty($_POST['msg'])) {
        $id = getLocationID();
        $message = $_POST['lmsg'];
        $lang = "en";
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

function getLocationID(){
    return "10.2.12";
};

function getQuestion(){
    return "To whom to may concern, I require assistance of X topic.";
}
