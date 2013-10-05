@echo off

title mysql
mysql --user=smanager --password=smanager < mysql-reset.sql
@if errorlevel 1 goto errormark

@goto endmark
:errormark
	echo   
    pause
:endmark
@ENDLOCAL

