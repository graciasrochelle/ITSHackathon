<?php
/**
 * Created by PhpStorm.
 * User: Dimmerworld
 * Date: 11/08/2018
 * Time: 8:13 PM
 */
function submit()
{
    if (isset($_POST['btnSubmit'])) {
        $id = $_POST['sID'];
        $message = $_POST['msg'];
        $lang = $_POST['sLang'];

        $myObj->name = "John";
        $myObj->age = 30;
        $myObj->city = "New York";

        echo "<h1>Student ID: " . $id . "</h1><h1> Message: " . $message . "</h1><h1>sLang: " . $lang . "</h1>";
    }
}