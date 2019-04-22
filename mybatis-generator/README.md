# 使用方法

* 1:
设定[generatorConfig.xml]文件，在文件最下端追加要生成表
		
		eg。
		<table tableName="relation" domainObjectName="Relation"
		 enableCountByExample="false" 
		 enableUpdateByExample="false" 
		 enableDeleteByExample="false" 
		 enableSelectByExample="false" 
		 selectByExampleQueryId="false"></table>

* 2:在cdm下执行下记命令行
	
		mybatis-generator>java -jar mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite
