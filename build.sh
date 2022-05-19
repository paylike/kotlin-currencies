#!/bin/zsh

## delete previous verions
rm ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/CurrencyCode.kt
rm ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt

## download source json
curl https://raw.githubusercontent.com/paylike/currencies/master/currencies.json > ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/currencies.json

## create the generated files
touch ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/CurrencyCode.kt
touch ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt

## generate the content of the kotlin files
node ./tools/builder.js

## remove source json
rm ./kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/currencies.json

