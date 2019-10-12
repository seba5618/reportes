rem el output  deberia ser el nro de factura y fecha meterlo en un vbs para que tome parametros
curl.exe -H "Content-Type: application/json" -o output.pdf --request POST  --data @envio.json http://localhost:8080/reporte/factura
pause