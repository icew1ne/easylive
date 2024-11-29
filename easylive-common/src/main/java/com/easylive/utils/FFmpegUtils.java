package com.easylive.utils;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;

@Component
public class FFmpegUtils {
    @Resource
    private AppConfig appConfig;

    public void createImageThumbnail(String filePath) {
        String CMD = "ffmpeg -i \"%s\" -vf scale=200:-1 \"%s\"";
        CMD = String.format(CMD, filePath, filePath + Constants.IMAGE_THUMBNAIL_SUFFIX);
        ProcessUtils.executeCommand(CMD, appConfig.getShowFFmegLog());
    }

    public Integer getVideoInfoDuration(String completeVideo) {
        final String CMD_GET_CODE = "ffprobe -v error -show_entries format=duration -of default=noprint wrappers=1:nokey=1 \"%s\"";
        String cmd = String.format(CMD_GET_CODE, completeVideo);
        String result = ProcessUtils.executeCommand(cmd, appConfig.getShowFFmegLog());
        if (StringTools.isEmpty(result)) {
            return 0;
        }
        result = result.replace("\n", "");
        return new BigDecimal(result).intValue();
    }

    public String getVideoCodec(String videoFilePath) {
        final String CMD_GET_CODE = "ffprobe -v error -select_streams v:0 -show_entries stream=code_name \"%s\"";
        String cmd = String.format(CMD_GET_CODE, videoFilePath);
        String result = ProcessUtils.executeCommand(cmd, appConfig.getShowFFmegLog());
        result = result.replace("\n", "");
        result = result.substring(result.lastIndexOf("=") + 1);
        String codec = result.substring(0, result.indexOf("("));
        return codec;
    }

    public void convertHevc2Mp4(String newFileName, String videoFilePath) {
        String CMD_HEVC_264 = "ffmpeg -i \"%s\" -c:v libx264 -crf 20 \"%s\" -y";
        String cmd = String.format(CMD_HEVC_264, newFileName, videoFilePath);
        ProcessUtils.executeCommand(cmd, appConfig.getShowFFmegLog());
    }

    public void convertVideo2Ts(File tsFolder, String videoFilePath) {
        final String CMD_TRANSFER_2TS = "ffmpeg -y -i \"%s\" -vcodec copy -acodec copy -vbsf h264_mp4toannexb \"%s\"";
        final String CMD_CUT_TS = "ffmpeg -i \"%s\" -c copy -map 0 -f segment -segment_list \"%s\" -segment_time 10 %s/%%4d.ts";
        String tsPath = tsFolder + "/" + Constants.TS_NAME;
        //生成.ts
        String cmd = String.format(CMD_TRANSFER_2TS, videoFilePath, tsPath);
        ProcessUtils.executeCommand(cmd, appConfig.getShowFFmegLog());
        //生成索引文件.m3u8 和切片.ts
        cmd = String.format(CMD_CUT_TS, tsPath, tsPath, tsFolder.getPath() + "/" + Constants.M3U8_NAME, tsFolder.getPath());
        //删除index.ts
        new File(tsPath).delete();
    }
}
