javac --source-path fc fc/*.java fc/util/*.java -d classes
javac --source-path . fc/**/*.java -d bin
javac --source-path . fc/*.java fc/util/*.java -d target  
jar -cvf fortunecookietest.jar -C classes .
java -cp fortunecookietest.jar fc.Server 12345 cookie_file_test.txt data


java -cp classes fc/Server 12345 cookie_file.txt          

java -cp classes fc/Client localhost:12345
