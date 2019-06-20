package com.idev.architecture.framework.service.impl;

import com.idev.architecture.framework.model.Customer;
import com.idev.architecture.framework.service.CustomerService;
import com.idev.architecture.framework.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<Customer>();
        Connection conn = null;
        try{
            conn = DatabaseUtil.getConnection();
            ResultSet rs = conn.prepareStatement(" select * from customer ")
                    .executeQuery();
            Customer customer = null;
            while(rs.next()){
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setId(rs.getLong("name"));
                customer.setId(rs.getLong("contact"));
                customer.setId(rs.getLong("telephone"));
                customer.setId(rs.getLong("email"));
                customer.setId(rs.getLong("remark"));

                customerList.add(customer);
            }

            return customerList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(conn);
        }

        return Collections.emptyList();
    }
}
