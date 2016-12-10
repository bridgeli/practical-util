package cn.bridgeli.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 主从数据源
 */
public class MasterSlaveDataSource extends AbstractRoutingDataSource{
    
    private MasterSlaveSelector masterSlaveSelector;
    
    /** 
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return masterSlaveSelector.get();
    }
    
    /**
     * Setter method for property <tt>masterSlaveSelector</tt>.
     * 
     * @param masterSlaveSelector value to be assigned to property masterSlaveSelector
     */
    public void setMasterSlaveSelector(MasterSlaveSelector masterSlaveSelector) {
        this.masterSlaveSelector = masterSlaveSelector;
    }
}
