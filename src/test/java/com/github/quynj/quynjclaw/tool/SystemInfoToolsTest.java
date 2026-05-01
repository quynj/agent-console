package com.github.quynj.quynjclaw.tool;

import org.junit.jupiter.api.Test;

class SystemInfoToolsTest {
    @Test
    void getSysInfo() {
        System.out.println(new SystemInfoTools().getSystemInfo());
    }
}