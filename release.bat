rem Packaging the 32-bit Java SE Runtime Environment 6u45 and the readme/license files with the 32-bit Windows executable
E:\Apps\Cmder\bin\7za.exe a C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x32\Oncoupe-0.1.0-alpha-win32.zip C:\Users\franc\Documents\GitHub\Oncoupe\README.pdf C:\Users\franc\Documents\GitHub\Oncoupe\LICENSE C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x32\jre\ C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x32\Oncoupe.exe

rem Packaging the 64-bit Java SE Runtime Environment 6u45 and the readme/license files with the 64-bit Windows executable
E:\Apps\Cmder\bin\7za.exe a C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x64\Oncoupe-0.1.0-alpha-win64.zip C:\Users\franc\Documents\GitHub\Oncoupe\README.pdf C:\Users\franc\Documents\GitHub\Oncoupe\LICENSE C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x64\jre\ C:\Users\franc\Documents\GitHub\Oncoupe\bin\win\x64\Oncoupe.exe

rem Packaging the readme/license files with the Java ARchive
E:\Apps\Cmder\bin\7za.exe a C:\Users\franc\Documents\GitHub\Oncoupe\bin\jvm\Oncoupe-0.1.0-alpha-jar.zip C:\Users\franc\Documents\GitHub\Oncoupe\README.pdf C:\Users\franc\Documents\GitHub\Oncoupe\LICENSE C:\Users\franc\Documents\GitHub\Oncoupe\out\artifacts\Oncoupe\Oncoupe.jar