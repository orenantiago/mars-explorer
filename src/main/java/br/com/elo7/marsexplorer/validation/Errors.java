package br.com.elo7.marsexplorer.validation;

public class Errors {

    public static MarsExplorerError ID_REQUIRED = new MarsExplorerError("id required");
    public static MarsExplorerError PROBE_REQUIRED = new MarsExplorerError("probe required");
    public static MarsExplorerError LAND_NOT_FOUND = new MarsExplorerError("land not found");
    public static MarsExplorerError PROBE_NOT_FOUND = new MarsExplorerError("probe not found");
}
