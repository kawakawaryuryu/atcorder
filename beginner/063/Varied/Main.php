<?php

$word = str_split(trim(fgets(STDIN), "\n"));
$unique_word = array_unique($word);

echo count($unique_word) == count($word) ? 'yes'.PHP_EOL : 'no'.PHP_EOL;
