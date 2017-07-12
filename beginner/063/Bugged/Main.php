<?php

$N = trim(fgets(STDIN));

for ($i = 0; $i < $N; $i++) {
    $scores[] = trim(fgets(STDIN));
}

echo get_max_score($scores) . PHP_EOL;


function get_max_score($scores) {

    sort($scores);
    $sum = array_sum($scores);

    if ($sum % 10 != 0) return $sum;

    foreach($scores as $score) {
        if (($sum - $score) % 10 != 0) {
            return ($sum - $score);
        }
    }

    return 0;
}
