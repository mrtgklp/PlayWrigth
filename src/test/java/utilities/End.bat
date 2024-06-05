@echo off
Title Process Killer by EvrenBey
Mode 80,5 & color 0B
Set "TmpFile=%Temp%\TmpFile.txt"
Set "Result=%Temp%\KillResult.txt"
If Exist "%TmpFile%" Del "%TmpFile%"
If Exist "%Resultat%" Del "%Resultat%"
echo(
echo     Enter the process name
echo(
set /p "process=What process(es) do you want to kill ? > "
cls & color 0C
Title Killing "%process%" ...
echo(
echo %date% *** %time% >"%TmpFile%"
For %%a in (%process%) Do Call :KillMyProcess %%a
Cmd /U /C Type "%TmpFile%" >"%Result%"
Timeout /T 2 /nobreak>nul
Start "" "%Result%"
::*********************************************************************************************
:KillMyProcess
Set "Process=%~1"
echo                 Killing "%process%" ...
echo "%Process%" | find /I " " >nul 2>&1  && (
    Taskkill /IM "%Process%" /F >>"%TmpFile%" 2>&1
) || (
    Taskkill /IM "%~n1.exe" /F >>"%TmpFile%" 2>&1
)
echo *****************************************************************************>>"%TmpFile%"
exit /b
::**********************************************************************************************