package org.sx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @TableId(value = "permission_id",type = IdType.AUTO)
    private Integer permissionId;
    private String permissionIcon;
    private String permissionIndex;
    private String label;
    private String permissionUrl;
    private Integer parentId;
    private Integer nodeId;
    @TableField(exist = false)  //一对多构建的方式
    private List<Permission> childrenList;  //  用来存放一级菜单包含的二级菜单
}
