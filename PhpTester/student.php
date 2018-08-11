

<html>
<body>
<?php
require_once("core.php");
submit();
?>
<form method="post">
    <label>Student ID</label>
    <input type="text" value="s1234567" name="sID"/>
    <label>Message</label>
    <input type="text" name="msg"/>
    <select name="sLang">
        <option value="en">English</option>
        <option value="zh">Chinese</option>
    </select>
    <button type="submit" name="btnSubmit"/>
</form>

</body>

</html>