<?php
if (!isset($_GET['EOInumber'])) {
    header('Location: apply.php');
    exit();
}

$EOInumber = htmlspecialchars($_GET['EOInumber']);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
</head>
<body>
    <h1>Application Submitted Successfully!</h1>
    <p>Your EOI number is: <?php echo $EOInumber; ?></p>
</body>
</html>
