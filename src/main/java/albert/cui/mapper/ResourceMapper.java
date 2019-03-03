package albert.cui.mapper;

import albert.cui.entity.Resource;

import java.util.List;

/**
 * @author albert.cui
 * @date 2019/3/3 18:21
 */
public interface ResourceMapper {
    /**
     * 获取所有的Resource
     * @return
     */
    List<Resource> getAllResources();
}
