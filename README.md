javac --source-path fc fc/*.java fc/util/*.java -d classes


java -cp classes fc/Server 12345 cookie_file.txt          

java -cp classes fc/Client localhost:12345
