

<html>
<body>
<?php
require_once("core.php");
submit();
?>
<form method="post">
    <label>Student ID</label>
    <input type="text" value="s12345678" name="sID"/>
    <label>Message</label>
    <input type="text" name="msg"/>
    <select name="sLang">
        <option value="en">English</option>
        <option value="zh">Chinese</option>
    </select>
    <button type="submit" name="btnSubmit">Submit</button>
</form>

</body>

</html>