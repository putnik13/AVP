@echo off

title mysql
mysql --user=smanager --password=smanager < mysql-preload.sql
@if errorlevel 1 goto errormark

@goto endmark
:errormark
	echo   
    pause
:endmark
@ENDLOCAL

