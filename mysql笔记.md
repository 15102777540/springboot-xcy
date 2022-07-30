
**mysql给已存在的主键字段设置自增 并设置初始值**
```sql
alter table `cov` modify id bigint(20) auto_increment;
alter table `cov` auto_increment=10000;
```

