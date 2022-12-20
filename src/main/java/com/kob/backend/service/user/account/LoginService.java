package com.kob.backend.service.user.account;

import java.util.Map;

public interface LoginService {
    public Map<Object,Object> getToken(Object username, Object password);
}
