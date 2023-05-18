<#
.SYNOPSIS
Downloads the 'winutils' repo needed to run Hadoop on Windows, as well as configures the HADOOP_HOME environment 
variable.

.DESCRIPTION
This function checks if the winutils folder exists and if it does not, clones the winutils repository from GitHub.
It then determines the absolute path to the highest versioned subfolder within the winutils folder and sets the
HADOOP_HOME environment variable to this path. If the HADOOP_HOME environment variable does not exist, it is created.

.PARAMETER None

.EXAMPLE
Assuming you trust this script, you will need to run the script via an ELEVATED command line
and possibly bypass the default Powershell execution policy.  Here are the command line steps below:
cd <same-directory-as-this-script>
powershell.exe -ExecutionPolicy Bypass -File Configure-Hadoop.ps1

#>

$currentPrincipal = New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())
if (-Not $currentPrincipal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    # otherwise we are done
    Write-Host "You are not an admin and this script requires elevated permissions.  Please execute this script as an admin..."
    return
}

# If we don't find the 'winutils' directory, assume it hasnt been setup yet and proceed.
$winutilsPath = "$PSScriptRoot\winutils"
if (Test-Path $winutilsPath) {
    # otherwise we are done
    Write-Host "Winutils folder already exists, exiting..."
    return
}

Write-Host "Cloning winutils repository..."
git clone https://github.com/steveloughran/winutils $winutilsPath

# find the highest versioned hadoop install in winutils
$hadoopHome = Get-ChildItem -Path $winutilsPath -Directory |
                Where-Object { $_.Name -match '\d+\.\d+\.\d+' } |
                Sort-Object -Descending |
                Select-Object -First 1 |
                Resolve-Path |
                Select-Object -ExpandProperty Path

# drop the prefixed "Microsoft.PowerShell.Core\FileSystem::"
$hadoopHome = Convert-Path $hadoopHome
                
Write-Host "HADOOP_HOME variable calculated to be: $hadoopHome"

Write-Host "Adding/updating HADOOP_HOME environment variable..."
[Environment]::SetEnvironmentVariable("HADOOP_HOME", $hadoopHome, [EnvironmentVariableTarget]::Machine)

Write-Host "Don't forget to restart IntelliJ so the added/updated environment variable value is loaded in the IDE."

