package com.xdcplus.interaction.generator;


import com.xdcplus.mp.generator.CodeGenerator;

/**
 * 代码生成器
 *
 * @author Rong.Jia
 * @date 2021/05/31
 */
public class Generator {

    public static void main(String[] args) {

        String database = "interaction_engine";
        String username = "root";
        String password = "123456";
        String host = "localhost";
        String port = "3306";

        CodeGenerator.autoGenerator(database, username, password, host, port, "Rong.Jia");

    }



}
