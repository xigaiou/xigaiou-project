package com.xigaiou.xigaiouproject.common.gqlSupport;

import com.alibaba.druid.util.JdbcUtils;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@NoArgsConstructor
public final class JdbcExportUtil {
    public static int executeSelect(DataSource dataSource, int fetchSize, String sql, Object[] args,
                                    JdbcExportUtil.ResultSetHandler handler) throws Exception{
        int nRows = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);

            for(int i = 0; i<args.length; i++){
                ps.setObject(i + 1, args[i]);
            }

            ps.setFetchSize(fetchSize);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] fieldNames = new String[columnCount];

            for(int i = 0; i < columnCount; i++){
                //toCamelCase的作用是驼峰转换
                fieldNames[i] = NameConvertUtil.toCamelCase(metaData.getColumnName(i + 1));
            }

            while(rs.next()){
                nRows += handler.processRow(fieldNames, rs);
            }
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(ps);
            JdbcUtils.close(connection);
        }
        return nRows;
    }

    public interface ResultSetHandler{
        int processRow(String[] fieldNames, ResultSet resultSet) throws Exception;
    }
}
