package com.cxz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
    
/**
 * <p>
 * 
 * </p>
 *
 * @author cxz
 * @since 2021-08-26
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("T_LEECODE")
public class TLeecode implements Serializable {

private static final long serialVersionUID=1L;

                @TableField("translatedTitle")
                    private String translatedtitle;

                @TableField("frontendId")
                    private String frontendid;

                @TableField("titleSlug")
                    private String titleslug;

                            private String title;

                            private String difficulty;

                @TableField("lastSubmittedAt")
                    private String lastsubmittedat;

                @TableField("numSubmitted")
                    private Integer numsubmitted;

                @TableField("lastSubmissionSrc")
                    private String lastsubmissionsrc;

                            private String typename;


        }