@echo off

title mysql
mysql --user=vrecorder --password=vrecorder < mysql-preload.sql
@if errorlevel 1 goto errormark

@goto endmark
:errormark
	echo   
    pause
:endmark
@ENDLOCAL

