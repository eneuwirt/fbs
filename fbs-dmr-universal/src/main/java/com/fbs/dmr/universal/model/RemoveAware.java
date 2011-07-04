package com.fbs.dmr.universal.model;

/**
 * Marker do not delete the entry, but mark as deleted only
 * 
 * @author neuwirt
 *
 */
public interface RemoveAware
{
    public boolean isDeleted();
    public void setDeleted(boolean deleted);
}
