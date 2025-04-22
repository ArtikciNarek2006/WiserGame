1) set environment var JRE_HOME to jdk path
cd $CATALINA_HOME/webapps/WiserWeb/WEB-INF/
javac -cp "<$CATALINA_HOME>/lib/*" -d ./classes ./src/main/java/com/wiserweb/*.java ./src/main/java/com/table/*.java ./src/main/java/com/util/*.java
mysql import db
start tomcat
