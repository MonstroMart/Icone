rem Packaging the 64-bit Java SE Runtime Environment 1.8.0_152 and the readme/license files with the 64-bit Windows executable
E:\Apps\Cmder\bin\7za.exe a C:\Users\franc\Documents\GitHub\Icone\bin\win\x64\Icone-0.1.0-alpha-win64.zip C:\Users\franc\Documents\GitHub\Icone\README.pdf C:\Users\franc\Documents\GitHub\Icone\LICENSE "C:\Program Files\Java\jdk1.8.0_152\jre" C:\Users\franc\Documents\GitHub\Icone\bin\win\x64\Icone.exe

rem Packaging the readme/license files with the Java ARchive
E:\Apps\Cmder\bin\7za.exe a C:\Users\franc\Documents\GitHub\Icone\bin\jvm\Icone-0.1.0-alpha-jar.zip C:\Users\franc\Documents\GitHub\Icone\README.pdf C:\Users\franc\Documents\GitHub\Icone\LICENSE C:\Users\franc\Documents\GitHub\Icone\out\artifacts\Icone\Icone.jar