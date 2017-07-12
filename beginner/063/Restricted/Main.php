<?php

$input = explode(' ', trim(fgets(STDIN)));
$A = $input[0];
$B = $input[1];

echo ($A + $B) >= 10 ? 'error' . PHP_EOL : ($A + $B) . PHP_EOL;
