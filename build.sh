#!/bin/zsh

## delete previous verions
rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/CurrencyCode.kt
rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt

## download source json
curl https://raw.githubusercontent.com/paylike/currencies/master/currencies.json > ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/currencies.json

## create the generated files
touch ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/CurrencyCode.kt
touch ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt

## generate the content of the generated kotlin files
node ./tools/builder.js

## remove source json
rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/currencies.json
