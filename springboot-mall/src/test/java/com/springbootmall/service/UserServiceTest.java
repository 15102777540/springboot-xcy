package com.springbootmall.service;

import com.springbootmall.pojo.ContainersTest;
import com.springbootmall.pojo.dto.*;
import com.springbootmall.util.*;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.springbootmall.mapper")
class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @Test
    void selectAll() {
        //TODO 测试  分页
        List<MenuDto> byUser = menuService.findByUser(1L);
        /*User user = new User();
        user.setId("12");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        //List<User> users = userService.selectAllbyQuery(queryWrapper);
        //List<User> userList = userService.selectAll();
        IPage<User> userIPage = userService.selectPage(new Page<>(1, 2),null);
        PageUtils<User> userPageUtils = new PageUtils<User>(userIPage.getTotal(), userIPage.getRecords(), userIPage.getCurrent(), userIPage.getSize());

        List<User> userIPageS = userService.selectPage(new Page<>(1, 3));*/
        /*userList.forEach(x->{
            System.out.println(x.getName());
        });
        userList.forEach(System.out::println);*/
        //Map<String> map = new HashMap();
        List<AuthUserDto> user = new ArrayList<>();
        Map<String, List<AuthUserDto>> map = new HashMap();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            user.add(new AuthUserDto(Integer.toString(i), "123", "(byte) 1", "123123"));
        }
        TEST(user.toArray(new AuthUserDto[0]));
        int b[][] = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(b));
        List<Integer> a = new ArrayList<>();
        TESTInt(a.toArray(new Integer[0]));
    }

    public void TEST(AuthUserDto... param) {
        System.out.println("param = " + JacksonUtil.obj2String(param));
    }

    public void TESTInt(Integer... param) {
        System.out.println("param = " + JacksonUtil.obj2String(param));
    }

    public void a(String... a){

    }

    @Test
    void Tests() {
        String a = "{\"msg\":\"审计查询成功！\",\"data\":[{\"id\":1341009796,\"remark\":null,\"user\":null,\"oldContainerLog\":{\"iycCntrno\":\"TCNU5511009\",\"iycInytm\":\"2021-11-09 15:03\",\"iycType\":\"WOY\",\"iycCszCsizecd\":\"40\",\"iycCtypecd\":null,\"iycCheightcd\":\"HC\",\"iycIsocd\":\"45G1\",\"iycCweight\":\"3600\",\"iycStsCstatuscd\":\"OE\",\"iycCstCopercd\":\"03005\",\"iycYlocation\":\"\",\"iycPotLdport\":\"ILHBT\",\"iycPotUnldport\":\"XXXXX\",\"iycPotDstport\":\"XXXXX\",\"iycPotTransport\":\"\",\"iycCstFrom\":\"\",\"iycCstTo\":\"\",\"iycOuttm\":\"\",\"iycDoordirection\":\"LS\",\"iycPassfg\":\"N\",\"iycStowagefg\":\"N\",\"iycGroupfg\":null},\"newContainerLog\":{\"iycCntrno\":\"TCNU5511009\",\"iycInytm\":\"2021-11-09 15:03\",\"iycType\":\"SAC\",\"iycCszCsizecd\":\"40\",\"iycCtypecd\":null,\"iycCheightcd\":\"HC\",\"iycIsocd\":\"45G1\",\"iycCweight\":\"3600\",\"iycStsCstatuscd\":\"OE\",\"iycCstCopercd\":\"03005\",\"iycYlocation\":\"A37253\",\"iycPotLdport\":\"ILHBT\",\"iycPotUnldport\":\"XXXXX\",\"iycPotDstport\":\"XXXXX\",\"iycPotTransport\":\"\",\"iycCstFrom\":\"\",\"iycCstTo\":\"\",\"iycOuttm\":\"\",\"iycDoordirection\":\"LS\",\"iycPassfg\":\"N\",\"iycStowagefg\":\"N\",\"iycGroupfg\":null}}],\"status\":\"success\"}";
       /* Map jsonToBean = FastjsonUtil.getJsonToBean(a, Map.class);
        System.out.println(jsonToBean);
        System.out.println(jsonToBean.get("data"));
        List<Map<String, Object>> data = FastjsonUtil.getJsonToListMap(jsonToBean.get("data").toString());*/
        //List<List> data = FastjsonUtil.getJsonToList(jsonToBean.get("data").toString(), List.class);
        Map mapData = JacksonUtil.string2Obj(a, Map.class);
       /* List<Map> data = JacksonUtil.string2Obj(mapData.get("data").toString(), new TypeReference<List<Map>>() {
        });*/
        LogUtils<Map> LogUtilsmap = new LogUtils<>();
        List<Map> data = (List<Map>) mapData.get("data");
        Iterator<Map> iterator = data.iterator();
        LinkedList<ContainersTest> mapList = new LinkedList<>();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            String id = next.get("id").toString();
            String remark = next.get("remark") == null ? "" : next.get("remark").toString();
            String user = next.get("user") == null ? "" : next.get("user").toString();
            ContainersTest oldContainerLog = JacksonUtil.getObjectMapper().convertValue(next.get("oldContainerLog"), ContainersTest.class);
            ContainersTest newContainerLog = JacksonUtil.getObjectMapper().convertValue(next.get("newContainerLog"), ContainersTest.class);
            System.out.println(id);
            oldContainerLog.setId(id);
            newContainerLog.setId(id);

            oldContainerLog.setRemark(remark);
            newContainerLog.setRemark(remark);

            oldContainerLog.setUser(user);
            newContainerLog.setUser(user);

            List<String> strings = LogUtilsmap.compareObject(oldContainerLog, newContainerLog);
            oldContainerLog.setCols(strings);
            newContainerLog.setCols(strings);
            oldContainerLog.setKey("旧值");
            newContainerLog.setKey("新值");
            mapList.add(oldContainerLog);
            mapList.add(newContainerLog);
        }
        for (int i = 0; i < 5; i++) {
            ContainersTest obj1 = mapList.get(0);
            ContainersTest obj2 = mapList.get(1);
            ContainersTest o1 = null;
            ContainersTest o2 = null;
            try {
                o1 = (ContainersTest) BeanUtils.cloneBean(obj1);
                o2 = (ContainersTest) BeanUtils.cloneBean(obj2);
                o1.setId("2649213053" + i);
                o2.setId("2649213053" + i);
                o2.setIycCheightcd("123");
                o2.setIycCstCopercd("5677");
                List<String> strings = LogUtilsmap.compareObject(o1, o2);
                strings.remove("key");
                o1.setCols(strings);
                o2.setCols(strings);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            mapList.add(o1);
            mapList.add(o2);
        }
        String s = JacksonUtil.obj2String(mapList);
        System.out.println(s);
    }

    /**
     * 多列求和
     */
    @Test
    public void TestNumber() {
        List<TestNumber> numList = new ArrayList<>();
        //创建空数组
        List<String> objects = Arrays.asList();
        /*numList.add(new TestNumber(new BigDecimal(3), new BigDecimal(6)));
        numList.add(new TestNumber(new BigDecimal(3), new BigDecimal(6)));
        numList.add(new TestNumber(new BigDecimal(3), new BigDecimal(6)));
        numList.add(new TestNumber(new BigDecimal(3), new BigDecimal(6)));*/
        /*numList.stream().collect(Collectors.reducing((sum) ->
             sum.getA()
        ));*/
        //System.out.println(collect);
    }

    @Test
    public void mergeData() {
        List<mergeData> numList = new ArrayList<>();
        numList.add(new mergeData("2021-10-1", "1", "你123好啊速度1", 0, 0));
        numList.add(new mergeData("2021-10-1", "1", "你好123啊速2度", 0, 0));
        numList.add(new mergeData("2021-10-1", "2", "你好啊213速3", 0, 0));
        numList.add(new mergeData("2021-10-1", "2", "你好231啊速度", 0, 0));
        numList.add(new mergeData("2021-10-12", "1", "你好啊31速度", 0, 0));
        numList.add(new mergeData("2021-10-12", "2", "你好21323啊速度", 0, 0));
        numList.add(new mergeData("2021-10-13", "1", "你好13321123啊速度", 0, 0));
        numList.add(new mergeData("2021-10-13", "1", "你好啊123速度", 0, 0));
        numList.add(new mergeData("2021-10-13", "2", "你好啊213速度", 0, 0));
        numList.add(new mergeData("2021-10-13", "3", "你好啊321速度", 0, 0));
        numList.add(new mergeData("2021-10-13", "3", "3", 0, 0));
        numList.add(new mergeData("2021-10-13", "3", "你好123啊速度", 0, 0));
        numList.forEach(r -> {

        });
        /*numList.stream().collect(Collectors.reducing((sum) ->
             sum.getA()
        ));*/
        //System.out.println(collect);
    }

    @Test
    public void TestService() throws InvocationTargetException, IllegalAccessException {
        UserDto admin = userService.findByName("admin");
        /*User u = new User();
        UserDto ud = new UserDto();
        u.setNickName("123123");
        u.setUsername("1231239999999999999999");
        BeanUtils.copyProperties(ud,u);*/
        System.out.println(admin);
    }

    @Test
    public void Testss() {
        List<TestNumber> testNumbers = new ArrayList<>();
        Random rd = new Random();
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名1", "语文", new BigDecimal(80), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名2", "语文", new BigDecimal(90), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名3", "数学", new BigDecimal(100), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名4", "数学", new BigDecimal(80), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名5", "英语", new BigDecimal(90), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名6", "英语", new BigDecimal(80), (double) rd.nextInt(100)));

        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名1", "数学", new BigDecimal(80), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名2", "数学", new BigDecimal(90), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名3", "英语", new BigDecimal(80), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名4", "英语", new BigDecimal(70), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名5", "语文", new BigDecimal(80), (double) rd.nextInt(100)));
        testNumbers.add(new TestNumber(new BigDecimal(0), new BigDecimal(0), "姓名6", "语文", new BigDecimal(100), (double) rd.nextInt(100)));

        //分组求和
        Map<String, Double> collect = testNumbers.stream().collect(Collectors.toMap(TestNumber::getName, TestNumber::getResult2, Double::sum));
        System.out.println(collect);

        Map<String, List<TestNumber>> collect1 = testNumbers.stream().collect(Collectors.groupingBy(TestNumber::getName));
        System.out.println(collect1);
        Map<String, Double> collect2 = testNumbers.stream().collect(Collectors.groupingBy(TestNumber::getName, Collectors.summingDouble(TestNumber::getResult2)));
        System.out.println(collect2);
        List<TestNumber> testNumberList = new ArrayList<>();
        for (String s : collect2.keySet()) {
            TestNumber t = new TestNumber();
            t.setName(s);
            t.setResult2(collect2.get(s));
            testNumberList.add(t);
        }
        testNumberList.sort(new Comparator<TestNumber>() {
            @Override
            public int compare(TestNumber o1, TestNumber o2) {
                return o2.getResult2().compareTo(o1.getResult2());
            }
        });
        /*testNumberList.forEach(System.out::println);*/
        for (TestNumber testNumber : testNumberList) {
            System.out.println(testNumber.getName() + " " + testNumber.getResult2());
        }

        List<BigDecimal> collect3 = testNumbers.stream().filter(x -> x.getA().equals("123")).map(TestNumber::getA).collect(Collectors.toList());
        boolean contains = collect3.contains("123");
        System.out.println(contains);
    }

    @Test
    public void Test5() throws ParseException {
        Map map = new HashMap();
        System.out.println(map.isEmpty());
        map.put("time","1654049594316");
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date((Long) map.get("time"));
        String a = "105A";
        String substring1 = a.substring(a.length() - 1);
        String substring = new StringBuilder(a).reverse().substring(0, 2);
        System.out.println(substring);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sf.parse("2022-05-25 22:20:30", new ParsePosition(0));
        Date parse1 = sf.parse("2022-05-25 21:20:30", new ParsePosition(0));
        Date parse2 = sf.parse("2022-05-25 23:20:30", new ParsePosition(0));
        int i = parse2.compareTo(parse);
    }

    @Test
    public void TestXlsx() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("temp/WeeklyData2022-02-21.xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        Workbook workbook = null;
        //workbook = new XSSFWorkbook(inputStream); //2007
        //HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
        //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx对于不同版本的EXCEL文档要使用不同的工具类，如果使用错了，会提示如下错误信息。
        String filename = classPathResource.getFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheetAt = workbook.getSheetAt(0);
        int numMergedRegions = sheetAt.getNumMergedRegions();
        for (int i = 0; i < sheetAt.getLastRowNum(); i++) {
            Row row = sheetAt.getRow(i);
            if (row != null) {
                MergedRegions(row, i, numMergedRegions, sheetAt);
                for (int j = 1; j < 19; j++) {
                    Cell templateCell = row.getCell(j);
                    if (templateCell != null) {
                        Cell newCell = row.createCell(j+18);
                        sheetAt.setColumnWidth(j+18, 6*256);
                        POIUtil.copyCell(templateCell, newCell);
                    }
                }

            }
        }
        String path = classPathResource.getFile().getPath();
        File newFile = new File(path.substring(0, path.lastIndexOf("temp\\WeeklyData2022-02-21.xlsx")), "123.xlsx");
        FileUtil.touch(newFile);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        workbook.write(outputStream);
    }

    public void MergedRegions(Row row, int r, int numMergedRegions, Sheet sheet) {
        int targetRowFrom;
        int targetRowTo;
        CellRangeAddress region = null;
        for (int i = 0; i < numMergedRegions; i++) {
            region = sheet.getMergedRegion(i);
            if (region.getFirstRow() >= 2 && region.getFirstRow() == r && region.getFirstColumn() > 0 &&region.getFirstColumn() < 20) {
                targetRowFrom = region.getFirstColumn() - 1 + 19;
                targetRowTo = region.getLastColumn() - 1 + 19;
                CellRangeAddress newRegion = region.copy();
                newRegion.setFirstRow(region.getFirstRow());
                newRegion.setFirstColumn(targetRowFrom);
                newRegion.setLastRow(region.getLastRow());
                newRegion.setLastColumn(targetRowTo);
                System.out.println(r);
                sheet.addMergedRegion(newRegion);
            }
        }
    }

    @Test
    public void TestBilling() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("temp/Formatofbillingdatarequistion(OP)(1).xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        Workbook workbook = null;
        //workbook = new XSSFWorkbook(inputStream); //2007
        //HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
        //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx对于不同版本的EXCEL文档要使用不同的工具类，如果使用错了，会提示如下错误信息。
        String filename = classPathResource.getFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheetAt = workbook.getSheetAt(0);
        POIUtil.copyRows(6, 37, 38, sheetAt);
        String path = classPathResource.getFile().getPath();
        File newFile = new File(path.substring(0, path.lastIndexOf("temp\\Formatofbillingdatarequistion(OP)(1)")), "1234.xlsx");
        FileUtil.touch(newFile);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        workbook.write(outputStream);

    }

    @Test
    public void WeekDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2022);
        calendar.set(Calendar.MONTH,7);
        calendar.set(Calendar.DATE,1);
        calendar.roll(Calendar.DATE,-1);
        int i1 = calendar.get(Calendar.DATE);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parse1 = LocalDate.parse("2021-10-25", dateTimeFormatter);
        LocalDate with2 = parse1.with(DayOfWeek.MONDAY);
        System.out.println(with2);
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        //指定时间
        LocalDateTime now1 = LocalDateTime.of(2022, Month.MARCH, 2, 0, 0);
        // 本周开始时间
        LocalDateTime with = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

        LocalDateTime with1 = now1.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));



        LocalDate parse = LocalDate.parse("2022-03-14", dateTimeFormatter);
        System.out.println(""+parse);
        //System.out.println(with.format(dateTimeFormatter));
        for (int i = 0; i < 7; i++) {
            //System.out.println(with.plusDays(i).format(dateTimeFormatter));
            //System.out.println(with1.plusDays(i).format(dateTimeFormatter));
            System.out.println(parse.plusDays(i).format(dateTimeFormatter));
        }
        // 本周结束时间
        //Date endTimeInWeek = now.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withDayOfWeek(DateTimeConstants.SUNDAY).toDate();
    }


}
