swagger访问路径：http://localhost:9090/swagger-ui.html

device_group
device_output
device_param
device_rule
device_tag
groups


//    @PostMapping("save/{mid}")
//    @ApiImplicitParam(name = "mid", value = "设备型号id", dataType = "int")
//    @ApiOperation(value = "添加设备", notes = "添加设备")
//    public String save(@PathVariable int mid) throws Exception {
//        Device device = new Device();
//        try {
//            String number = NumberUtils.createNumberKey();
//            device.setNumber(number);
//            String s = httpAPIService.doGet("http://192.168.0.25:8888/model/selectOne/" + mid);
//            //获取型号编号对象
//            Model models = mapper.readValue(s, Model.class);
//            System.out.println(models);
//            device.setDmNum(models.getNumber());
//            device.setName(models.getName());
//            device.setDevSn(models.getManuNum() + models.getNumber());
//            device.setIsDel("0");
//
//            int result = deviceService.save(device);
//            if (result >= 1) {
//                return "添加成功";
//            } else {
//                return "添加失败";
//            }
//
//        } catch (Exception exception) {
//            log.error("添加设备", exception);
//            throw exception;
//        }
//    }