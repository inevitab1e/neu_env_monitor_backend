package com.neu.edu.dao;

import com.neu.edu.common.dao.BaseDao;
import com.neu.edu.entity.StatisticsEntity;
import com.neu.edu.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Mapper
public interface StatisticsDao extends BaseDao<StatisticsEntity> {

    @Select("SELECT gp.province_id,\n" +
            "       gp.province_abbr,\n" +
            "       gp.province_name,\n" +
            "       COUNT(CASE WHEN s.so2_level >= 3 THEN 1 ELSE NULL END) AS so2_exceeded_count,\n" +
            "       COUNT(CASE WHEN s.co_level >= 3 THEN 1 ELSE NULL END)  AS co_exceeded_count,\n" +
            "       COUNT(CASE WHEN s.spm_level >= 3 THEN 1 ELSE NULL END) AS spm_exceeded_count,\n" +
            "       COUNT(CASE WHEN s.aqi_id >= 3 THEN 1 ELSE NULL END)    AS aqi_exceeded_count\n" +
            "FROM grid_province gp\n" +
            "         LEFT JOIN statistics s\n" +
            "                   ON gp.province_id = s.province_id\n" +
            "GROUP BY gp.province_id\n" +
            "ORDER BY gp.province_id;")
    List<ProvinceAqiIndexVO> getProvinceAqiIndexExceededInfo();

    @Select("WITH all_aqi_ids AS (\n" +
            "            SELECT DISTINCT aqi_id\n" +
            "            FROM statistics\n" +
            "            UNION\n" +
            "            SELECT aqi_id\n" +
            "            FROM aqi\n" +
            "        )\n" +
            "\n" +
            "        SELECT aa.aqi_id aqi_id,\n" +
            "               aqi.aqi_explain aqi_explain,\n" +
            "               COALESCE(COUNT(s.aqi_id), 0) AS count\n" +
            "        FROM all_aqi_ids aa\n" +
            "                 LEFT JOIN statistics s ON aa.aqi_id = s.aqi_id\n" +
            "                 LEFT JOIN aqi ON aa.aqi_id = aqi.aqi_id\n" +
            "        GROUP BY aa.aqi_id, aqi.aqi_explain\n" +
            "        ORDER BY aa.aqi_id")
    List<AqiCountVO> getAqiCountInfo();

    @Select("WITH recent_months AS (\n" +
            "            SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL t.n MONTH), '%Y-%m') AS month\n" +
            "            FROM (\n" +
            "                     SELECT 0 AS n\n" +
            "                     UNION ALL\n" +
            "                     SELECT 1\n" +
            "                     UNION ALL\n" +
            "                     SELECT 2\n" +
            "                     UNION ALL\n" +
            "                     SELECT 3\n" +
            "                     UNION ALL\n" +
            "                     SELECT 4\n" +
            "                     UNION ALL\n" +
            "                     SELECT 5\n" +
            "                     UNION ALL\n" +
            "                     SELECT 6\n" +
            "                     UNION ALL\n" +
            "                     SELECT 7\n" +
            "                     UNION ALL\n" +
            "                     SELECT 8\n" +
            "                     UNION ALL\n" +
            "                     SELECT 9\n" +
            "                     UNION ALL\n" +
            "                     SELECT 10\n" +
            "                     UNION ALL\n" +
            "                     SELECT 11\n" +
            "                 ) AS t\n" +
            "        )\n" +
            "\n" +
            "        SELECT rm.month             AS month,\n" +
            "               COALESCE(s.count, 0) AS count\n" +
            "        FROM recent_months rm\n" +
            "                 LEFT JOIN (\n" +
            "            SELECT DATE_FORMAT(STR_TO_DATE(confirm_date, '%Y-%m-%d'), '%Y-%m') AS month,\n" +
            "                   COUNT(*)                                                    AS count\n" +
            "            FROM statistics\n" +
            "            WHERE aqi_id >= 3\n" +
            "              AND STR_TO_DATE(confirm_date, '%Y-%m-%d') >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)\n" +
            "            GROUP BY DATE_FORMAT(STR_TO_DATE(confirm_date, '%Y-%m-%d'), '%Y-%m')\n" +
            "        ) s ON rm.month = s.month\n" +
            "        ORDER BY rm.month DESC;")
    List<AqiMonthCountVO> getAqiMonthCountInfo();

    @Select("SELECT ROUND((COUNT(DISTINCT CASE WHEN gc.city_level = 2 THEN gc.city_id END) /\n" +
            "                      (SELECT COUNT(*) FROM grid_city WHERE city_level = 2)) *\n" +
            "                     100)                                                            AS provincial_capital_city_coverage,\n" +
            "               ROUND((COUNT(DISTINCT CASE WHEN gc.city_level >= 1 THEN gc.city_id END) /\n" +
            "                      (SELECT COUNT(*) FROM grid_city WHERE city_level >= 1)) * 100) AS big_city_coverage\n" +
            "        FROM grid_city gc\n" +
            "                 LEFT JOIN\n" +
            "             grid_member gm ON gc.city_id = gm.city_id\n" +
            "        WHERE gm.gm_id IS NOT NULL;")
    CoverageVO getCoverageInfo();

    @Select("SELECT COUNT(*) total,\n" +
            "        COUNT(CASE WHEN s.aqi_id >= 1 AND s.aqi_id <= 2 THEN s.id END) good_count,\n" +
            "        COUNT(CASE WHEN s.aqi_id >= 3 AND s.aqi_id <= 6 THEN s.id END) exceeded_count\n" +
            "        FROM statistics s")
    SummaryDataVO getSummaryDataInfo();
}