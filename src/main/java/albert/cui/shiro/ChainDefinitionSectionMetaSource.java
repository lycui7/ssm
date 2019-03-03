package albert.cui.shiro;

import albert.cui.entity.Resource;
import albert.cui.mapper.ResourceMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

/**
 * @author albert.cui
 * @date 2019/3/3 14:57
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
    @Autowired
    ResourceMapper resourceMapper;
    private String filterChainDefinitions;
    public static final String PERMISSION_STRING = "perms[\"{0}\"]";

    @Override
    public Ini.Section getObject() throws BeansException {
        //获取所有的resource
        List<Resource> list = resourceMapper.getAllResources();
        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //section就是filterChainDefinitionMap
        for (Iterator<Resource> iterator = list.iterator(); iterator.hasNext(); ) {
            Resource resource = iterator.next();
            //如果不为空则添加到section中
            if (StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {
                section.put(resource.getValue(), MessageFormat.format(PERMISSION_STRING, resource.getPermission()));
            }
        }
        return section;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
