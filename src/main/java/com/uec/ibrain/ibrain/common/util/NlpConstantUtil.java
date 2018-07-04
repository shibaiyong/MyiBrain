package com.uec.ibrain.ibrain.common.util;

/**
 * 语义分析公共对象
 * 
 * @author 陈金梁
 *
 */
public class NlpConstantUtil{

	// 玻森密匙:第一个是企业级，第二个是个人(开发测试阶段使用个人密钥，生产阶段使用企业密钥)
	 public static final String BOSON_TOKEN = "LvjHC6gJ.5806.8_MZOWZkUxW9";//生产使用
	// public static final String BOSON_TOKEN = "AemJ2VhU.9036.w_FsA8uLir1T";// 开发测试使用
	/**
	 * <p>Description:玻森地址 </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_URL
	 */
	public static final String BOSON_URL = "http://api.bosonnlp.com";

	/**
	 * <p>Description:玻森每次传入分析篇数 </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_ANALYSE_NUM
	 */
	public static final int BOSON_ANALYSE_NUM = 20;

	/**
	 * <p>Description:玻森只有典型意见有，好处：可以加密访问，评论分析有用、坏处：公司不主支持 </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_URL_VIEW
	 */
	public static final String BOSON_URL_VIEW = "https://api.bosonview.com";
	/**
	 * <p>Description:玻森实体抽取访问URL </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_TAG_URL
	 */
	public static final String BOSON_TAG_URL = BOSON_URL + "/tag/analysis";
	
	/**
	* <p>Description:NLP_TAG_URL</p>
	* <p>Author:xkwang/王西坤</p>
	*/
	//public static final String NLP_TAG_URL = "http://10.6.0.8:1237/posTag";

	/**
	 * <p>Description: 玻森实体抽取访问URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_ENTITY_URL
	 */
	public static final String BOSON_ENTITY_URL = BOSON_URL + "/ner/analysis";
	
	/**
	* <p>Description:NLP_ENTITY_URL</p>
	* <p>Author:xkwang/王西坤</p>
	*/
	//public static final String NLP_ENTITY_URL = "http://10.6.0.8:1237/ner";
	/**
	 * <p>Description:玻森实体词分析参数 </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_ENTITY_SENSITIVITY
	 */
	public static final int BOSON_ENTITY_SENSITIVITY = 3;

	/**
	 * <p>Description: 玻森关键词抽取URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_KEYWORDS_URL
	 */
	public static final String BOSON_KEYWORDS_URL = BOSON_URL + "/keywords/analysis";
	
	/**
	 * <p>Description:玻森返回前几个关键词 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_KEY_WORDS_COUNT 
	 */
	public static final int BOSON_KEY_WORDS_COUNT = 100;
	
	/**
	 * <p>Description:玻森返回前n个典型意见结果 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_KEY_WORDS_COUNT 
	 */
	public static final int BOSON_COMMENT_COUNT = 15;

	/**
	 * <p>Description: 玻森新闻摘要URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_SUMMARY_URL
	 */
	public static final String BOSON_SUMMARY_URL = BOSON_URL + "/summary/analysis";

	/**
	 * <p>Description:新闻摘要字数限制</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SUMMARY_PERCENTAGE 
	 */
	public static final double BOSON_SUMMARY_PERCENTAGE = 0.3;
	
	/**
	 * <p>Description:玻森情感分析URL </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_SENTIMENT_URL
	 */
	public static final String BOSON_SENTIMENT_URL = BOSON_URL + "/sentiment/analysis";
	
	/**
	 * nlp情感分析
	 */
	/*public static final String NLP_SENTIMENT_NEWSCONTENT = "newsContent";
	public static final String NLP_SENTIMENT_NEWSCOMMENT = "newsComment";
	public static Map<String, String> getSentientDomainMap(){
		Map<String , String> sentientDomainMap = new HashMap<String, String>();
		sentientDomainMap.put(NLP_SENTIMENT_NEWSCONTENT,"domain=newsContent");
		sentientDomainMap.put(NLP_SENTIMENT_NEWSCOMMENT,"domain=newsComment");
		return sentientDomainMap;
	}*/

	/**
	 * <p>Description: 玻森情感分析类型，默认为分析新闻类型</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_SENTIMENT_TYPE
	 */
	public static final String BOSON_SENTIMENT_NEWS_URL = BOSON_SENTIMENT_URL + "?news";

	/**
	 * <p>Description: 玻森情感分析类型集合</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields BOSON_SENTIMENT_TYPE
	 */
	public static final String[] BOSON_SENTIMENT_TYPE = {"auto","kitchen","food","news","weibo"};
	
//	public static final String[] NLP_SENTIMENT_TYPE = {"newsContent","newsComment"};
	
	/**
	 * <p>Description:玻森情感分析结果为正面 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SENTIMENT_POSITIVE 
	 */
	public static final String BOSON_SENTIMENT_POSITIVE = "正面";
	
	/**
	 * <p>Description:玻森情感分析结果为负面 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SENTIMENT_NEGATIVE 
	 */
	public static final String BOSON_SENTIMENT_NEGATIVE = "负面";
	
	/**
	 * <p>Description:玻森情感分析结果为中性 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SENTIMENT_NEUTRAL 
	 */
	public static final String BOSON_SENTIMENT_NEUTRAL = "中性";
	
	/**
	 * <p>Description:情感分析结果中性判断下限 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SENTIMENT_NEUTRAL_MIN 
	 */
	public static final double BOSON_SENTIMENT_NEUTRAL_MIN = 0.4; 
	public static final double NLP_SENTIMENT_CONTENT_MIN = 0.4; 
	public static final double NLP_SENTIMENT_COMMENT_MIN = 0.17; 
	
	/**
	 * <p>Description:情感分析结果中性判断上限 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields BOSON_SENTIMENT_NEUTRAL_MAX 
	 */
	public static final double BOSON_SENTIMENT_NEUTRAL_MAX = 0.6; 
	public static final double NLP_SENTIMENT_CONTENT_MAX = 0.6; 
	public static final double NLP_SENTIMENT_COMMENT_MAX = 0.83; 
	
	/**
	 * <p>Description: 新闻类别情感分析</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields BOSON_SENTIMENT_DEFAULT_TYPE 
	 */
	public static final String BOSON_SENTIMENT_DEFAULT_TYPE = "news";
	
	/**
	 * <p>Description: 微博类别情感分析</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields BOSON_SENTIMENT_TYPE_WEIBO 
	 */
	public static final String BOSON_SENTIMENT_TYPE_WEIBO = "weibo";
	
	/**
	 * <p>Description: 汽车类别情感分析</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields BOSON_SENTIMENT_TYPE_AUTO 
	 */
	public static final String BOSON_SENTIMENT_TYPE_AUTO = "auto";

	// 玻森典型意见URL
	public static final String BOSON_COMMENT_PUSH = BOSON_URL + "/comments/push/";//
	public static final String BOSON_COMMENT_ANALYSIS = BOSON_URL + "/comments/analysis/";
	public static final String BOSON_COMMENT_STATUS = BOSON_URL + "/comments/status/";
	public static final String BOSON_COMMENT_RESULT = BOSON_URL + "/comments/result/";
	public static final String BOSON_COMMENT_CLEAR = BOSON_URL + "/comments/clear/";
	
	// 玻森新闻聚类URL
	public static final String BOSON_CLUSTER_PUSH = BOSON_URL + "/cluster/push/";// 请把TASK_ID换成您自己的ID，下同
	public static final String BOSON_CLUSTER_ANALYSIS = BOSON_URL + "/cluster/analysis/";
	public static final String BOSON_CLUSTER_STATUS = BOSON_URL + "/cluster/status/";
	public static final String BOSON_CLUSTER_RESULT = BOSON_URL + "/cluster/result/";
	public static final String BOSON_CLUSTER_CLEAR = BOSON_URL + "/cluster/clear/";

	/**
	 * <p>Description:iNlp URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_URL
	 */
//	public static final String INLP_URL = "http://10.43.4.207:1234";

	/**
	 * <p>Description: iNlp词性分析URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_TAG_URL
	 */
//	public static final String INLP_TAG_URL = INLP_URL + "/splitWords";

	/**
	 * <p>Description:iNlp 实体词分析URL </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_ENTITY_URL
	 */
//	public static final String INLP_ENTITY_URL = INLP_URL + "/ner";
	
	/**
	 * <p>Description: iNLP实体分析敏感参数，但目前振兴没有提供传参</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_ENTITY_SENSITIVITY 
	 */
	public static final int INLP_ENTITY_SENSITIVITY = 3;

	/**
	 * <p>Description:iNlp 关键词分析URL </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_KEYWORDS_URL
	 */
//	public static final String INLP_KEYWORDS_URL = INLP_URL + "/keyWords";

	/**
	 * <p>Description: iNlp 新闻摘要分析URL</p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_SUMMARY_URL
	 */
//	public static final String INLP_SUMMARY_URL = INLP_URL + "/summary";
	
	/**
	 * <p>Description: iNlp 新闻摘要字数比例</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_SUMMARY_PERCENTAGE 
	 */
	public static final double INLP_SUMMARY_PERCENTAGE = 0.4;

	/**
	 * <p>Description:iNlp每次传入分析篇数 </p>
	 * <p>Author:sq/孙强</p>
	 * 
	 * @Fields INLP_ANALYSE_NUM
	 */
	public static final int INLP_ANALYSE_NUM = 100;

	/**
	 * <p>Description: inlp返回前几个关键词</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_KEY_WORDS_COUNT 
	 */
	public static final int INLP_KEY_WORDS_COUNT = 20;
	
	/**
	 * <p>Description:iNlp聚类分析URL </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_CLUSTER_URL 
	 */
//	public static final String INLP_CLUSTER_URL = INLP_URL + "/hotCluster";
	
	/**
	 * <p>Description:iNlp聚类分析默认聚类半径 </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_CLUSTER_RADIUS 
	 */
	public static final double INLP_CLUSTER_RADIUS = 0.55;
	
	/**
	 * <p>Description:iNlp聚类分析默认返回聚类结果簇</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields INLP_CLUSTER_COUNT 
	 */
	public static final int INLP_CLUSTER_COUNT = 30;
	
	/**
	 * <p>Description: 玻森分析</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields NLP_TYPE_BOSON 
	 */
	public static final String NLP_TYPE_BOSON = "boson";
	
	/**
	 * <p>Description: inlp</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields NLP_TYPE_INLP 
	 */
	public static final String NLP_TYPE_INLP = "inlp";
	
	/**
	 * <p>Description:系统停用词id </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields SYS_STOP_WORD 
	 */
	public final static String SYS_STOP_WORD = "sys_stopword";
	
	/**
	 * <p>Description:自定义提用词id </p>
	 * <p>Author:sq/孙强</p>
	 * @Fields CUSTOM_STOP_WORD 
	 */
	public final static String CUSTOM_STOP_WORD = "cus_stopword";
	
	/**
	 * <p>Description: 典型意见分析任务前缀</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields COMMENT_TASKID_PRE 
	 */
	public final static String COMMENT_TASKID_PRE = "uec_Option_";
	
	/**
	 * <p>Description: 分析已完成</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_DONE 
	 */
	public final static String STATUS_DONE = "DONE";
	
	/**
	 * <p>Description: （在调用分析时）未找到任何数据</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_NOT_FOUND 
	 */
	public final static String STATUS_NOT_FOUND = "NOT FOUND";
	
	/**
	 * <p>Description: （在调用分析时）未找到任何数据编码</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_NOT_FOUND_CODE 
	 */
	public final static String STATUS_NOT_FOUND_CODE = "404";
	
	/**
	 * <p>Description: 成功接收到分析请求</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_RECEIVED 
	 */
	public final static String STATUS_RECEIVED = "ERROR";
	
	/**
	 * <p>Description: 数据分析正在进行中</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_RUNNING 
	 */
	public final static String STATUS_RUNNING = "RUNNING";
	
	/**
	 * <p>Description: 分析遇到错误退出</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields STATUS_ERROR 
	 */
	public final static String STATUS_ERROR = "ERROR";
	
	/**
	 * <p>Description: 前方记录使用条数文件路径</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields QIANFANG_FILE_PATH 
	 */
	public final static String QIANFANG_FILE_PATH = "/common/auth/authentication.txt";
	
	/**
	 * <p>Description:前方情感分析每天可使用量</p>
	 * <p>Author:sq/孙强</p>
	 * @Fields QIANFANG_SENTIMENT_DEFULTNUM 
	 */
	public final static int QIANFANG_SENTIMENT_DEFULT_NUM = 1000;
	
}
