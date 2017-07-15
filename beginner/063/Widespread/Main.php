<?php

fscanf(STDIN, "%d %d %d", $N, $A, $B);

for($i = 0; $i < $N; $i++) {
    $h[] = trim(fgets(STDIN));
}

//$count = simple_solve($h, $N, $A, $B);
$count = binary_search_solve($h, $N, $A, $B);

echo $count . PHP_EOL;


function simple_solve($h, $N, $A, $B) {
    $array = $h;
    $count = 0;

    do {
        $count++;
        rsort($h);

        // 最大の魔物の体力から$Aを引く
        $h[0] -= $A;

        for($i = 1; $i < count($h); $i++) {
            // その他の魔物の体力から$Bを引く
            $h[$i] -= $B;
        }

        $array = array_filter($h, function($element) {
            return $element > 0;
        });

    } while(!empty($array));

    return $count;
}

function binary_search_solve($hp, $N, $A, $B) {
    $min = 0;
    $max = 10000000000;

    while($max - $min > 1) {

        $burst_count = floor(($min + $max) / 2);

        $count = 0;
        foreach($hp as $h) {
            if ($h > $B * $burst_count) {
                $count += ceil(($h - $B * $burst_count) / ($A - $B));
            }
        }

        if ($count <= $burst_count) {
            $max = $burst_count;
        } else {
            $min = $burst_count;
        }

    }

    return $max;
}
