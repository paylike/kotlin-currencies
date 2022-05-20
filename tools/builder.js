
const generationPath = './../kotlin-currencies/src/main/java/io/paylike/kotlin_currencies/generated/';
const inputJson = 'currencies.json';
const outputCodes = 'CurrencyCode.kt';
const outputCurrencies = 'PaylikeCurrencyCollection.kt';

const initialGeneratedText = "// GENERATED CODE - DO NOT MODIFY BY HAND\n\n// **************************************************************************\n// CurrencyGenerator\n// **************************************************************************\n\npackage io.paylike.kotlin_currencies.generated\n\n";
const currencyCodeClassName = "CurrencyCode";
const paylikeCurrencyCollectionClassName = "PaylikeCurrencyCollection";
const paylikeCurrencyName = "PaylikeCurrency";
const collectionName = "currencies";

const currencies = require(generationPath + inputJson);
const fs = require('fs');
const path = require('path')

// Generating the classes
// initial string of currency code enum class
var currencyCodeString = 
    initialGeneratedText + 
    "enum class " +
    currencyCodeClassName +
    " {\n";
// initial string of currency collection class
var paylikeCurrencyString = 
    initialGeneratedText + 
    "import io.paylike.kotlin_currencies." +
    paylikeCurrencyName +
    "\n\nobject " +
    paylikeCurrencyCollectionClassName + 
    " {\n\tval " +
    collectionName +
    ": Map<" +
    currencyCodeClassName +
    ", " +
    paylikeCurrencyName +
    "> = mapOf(\n";
// iterating through the input json
for (let i = 0; i < currencies.length; i++) {
    const currency = currencies[i];

    // enum class of codes
    currencyCodeString += "\t" + currency.code;
    if (i < currencies.length - 1)
    {
        currencyCodeString += ","
    }
    currencyCodeString += "\n"
    // 

    // static class of currency collection
    paylikeCurrencyString += 
        "\t\t" + 
        currencyCodeClassName +
        "." +
        currency.code +
        " to " +
        paylikeCurrencyName +
        "(\"" +
        currency.code +
        "\", \"" +
        currency.currency +
        "\", " +
        currency.numeric.replace(/^0+(?!\.|$)/, '') +
        ", " +
        currency.exponent +
        ", funding = " ;
    if (currency.funding == true) 
    {
        paylikeCurrencyString += currency.funding;
    }
    else
    {
        paylikeCurrencyString += "false";
    }
    paylikeCurrencyString += ", deprecated = ";
    if (currency.deprecated == true) 
    {
        paylikeCurrencyString += currency.deprecated;
    }
    else
    {
        paylikeCurrencyString += "false";
    }
    paylikeCurrencyString += ")";
    if (i < currencies.length - 1)
    {
        paylikeCurrencyString += ","
    }
    paylikeCurrencyString += "\n"
    //
}
// closing the class file strings
currencyCodeString += "}\n";
paylikeCurrencyString += "\t)\n}\n";

// Writing the files
var file = path.join(__dirname, generationPath, outputCodes);
fs.writeFileSync(file, currencyCodeString);

file = path.join(__dirname, generationPath, outputCurrencies);
fs.writeFileSync(file, paylikeCurrencyString);