
printf  "Getting Products \n"
curl --location --request GET 'http://localhost:8080/v1/products'
printf  "\n Purchase Item \n"
curl --location --request POST 'http://localhost:8080/v1/customers/2/cart' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productIds": [4]
}
'
printf  "\n Done \n"