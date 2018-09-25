#!/bin/sh

echo "Listing JVM MBeans..."
curl -o ListJVMBeans.java https://raw.githubusercontent.com/goranceko/mbeans/master/ListJVMBeans.java
javac ListJVMBeans.java
java ListJVMBeans
rm ListJVMBeans.class
echo "file created: jvm-mbeans"
