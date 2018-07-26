package com.fzz.cloud.fzzcloudlockingSystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fzz.cloud.fzzcloudlockingSystem.entity.subset.UserSubset;

public class UserSubsetTreeUtils {

	public static List<String> returnList = new ArrayList<String>();

	public static Map<String, List<UserSubset>> returnArrMap(List<String> groupList, List<UserSubset> listUserSubset) {

		Map<String, List<UserSubset>> arrMap = new HashMap<String, List<UserSubset>>(groupList.size());

		for (int k = 0; k < groupList.size(); k++) {
			String pid = groupList.get(k);
			List<UserSubset> tempTreeList = new ArrayList<UserSubset>();
			for (int i = 0; i < listUserSubset.size(); i++) {
				UserSubset userSet = listUserSubset.get(i);
				/*
				 * 将同一父ID的tree添加到同一个List中,最后将List放入Map.. arrMap.put(pid,
				 * tempTreeList); 这点虽然不复杂,但这是整个思索的中心,
				 */
				String parentId = userSet.getParentId();
				if (pid == null) {
					pid = "null";
				}
				if (parentId == null) {
					parentId = "null";
				}
				if (pid.equals(parentId)) {
					tempTreeList.add(userSet);
				}
			}

			// 最后将List放入Map.. key就是这组List<Tree>父ID, value就是这组List
			arrMap.put(pid, tempTreeList);
		}

		return arrMap;
	}

//	public static boolean isInsert(List<UserSubset> mapForSubset, String parentId) {
//		boolean flag = false;
//		for (UserSubset userSubset : mapForSubset) {
//			if (userSubset.getStatu() == 0) {
//				if(parentId == null) {
//					return true;
//				}
//				if (userSubset.getId().equals(parentId)) {
//					flag = true;
//					return flag;
//				} else if (userSubset.getChildrenList() != null && userSubset.getChildrenList().size() != 0) {
//					flag = isInsert(userSubset.getChildrenList(), parentId);
//				}
//			} 
//		}
//
//		return flag;
//	}
	
	public static boolean isInsert(List<UserSubset> mapForSubset, String parentId) {
		boolean flag = false;
		for (UserSubset userSubset : mapForSubset) {
			if (userSubset.getStatu() == 0) {
				if(parentId == null) {
					return true;
				}
				if (userSubset.getId().equals(parentId)) {
					flag = true;
					return flag;
				} else if (userSubset.getChildrenList() != null && userSubset.getChildrenList().size() != 0) {
					flag = isInsert(userSubset.getChildrenList(), parentId);
				}
			} 
		}

		return flag;
	}

	public  static List<String> isSubset(List<UserSubset> mapForTree, String parentId) {
		for (UserSubset userSubset2 : mapForTree) {
			String parentId2 = userSubset2.getParentId();
			if (parentId2 == null) {
				parentId2 = "null";
			}
			if (userSubset2.getStatu() == 0) {
				if (userSubset2.getId().equals(parentId)) {// 表示当前用户
					if (userSubset2.getChildrenList() != null && userSubset2.getChildrenList().size() != 0) {

						isSubset(userSubset2.getChildrenList(), userSubset2.getId());
						returnList.add(userSubset2.getUserId());
					} else {
						returnList.add(userSubset2.getUserId());
						continue;
					}
				} else if (parentId2.equals(parentId)) {
					if (userSubset2.getChildrenList() != null && userSubset2.getChildrenList().size() != 0) {

						isSubset(userSubset2.getChildrenList(), userSubset2.getId());
						returnList.add(userSubset2.getUserId());
					} else {
						returnList.add(userSubset2.getUserId());
						continue;
					}
				} else if (userSubset2.getChildrenList() != null && userSubset2.getChildrenList().size() != 0) {
					isSubset(userSubset2.getChildrenList(), parentId);
				} else {
					continue;
				}

			} else {
				continue;
			}
		}

		return returnList;
	}

	/**
	 * No.2: 让节点与子节点之间彼此关联,并返回树的根 数据库格式并没有换,只是建立了关联
	 * 
	 * @param arrMap
	 */
	public static List<UserSubset> MapForSubset(Map<String, List<UserSubset>> arrMap) {

		// 所以pid的集成
		Set<String> pidSet = arrMap.keySet();

		// 遍历所有的父ID,然后与所以的节点比对,父id与id相同的 //找出对应的tree节点,然后将该节点的
		for (Iterator<String> it = pidSet.iterator(); it.hasNext();) {

			String pid = (String) it.next();

			/*
			 * 按分组的方式与pid比对. 如果找到,那么将该pid分组的List,做为子节点 赋值给该找到的节点的
			 * setChildrenList(list),同时也将找到节点赋值List内所有子节点的parentObj
			 * 
			 */
			for (Iterator<String> it2 = pidSet.iterator(); it2.hasNext();) {

				String key = (String) it2.next();
				// 不查找自己的分组
				if (pid.equals(key)) {
					// break;
				}

				List<UserSubset> list = arrMap.get(key);

				// No.3:找出对应的tree父节点对象
				UserSubset parentTree = indexOfList(list, pid);

				if (parentTree != null) {
					// 通过pid在Map里找出节点的子节点.
					List<UserSubset> childrenHereList = arrMap.get(pid);

					// 这里是我自己定义的变成成,都不一样.所以需要自定义
					// 把子节点List赋值给Tree节点的Children
					parentTree.setChildrenList(childrenHereList);

					// 这里是我自己定义的变是,都不一样.所以需要自定义
					// 与上面相反,这是 把父节点对象赋值给Tree节点的parentObj
					for (int i = 0; i < childrenHereList.size(); i++) {
						UserSubset childrenHereTree = childrenHereList.get(i);
						childrenHereTree.setParentObj(parentTree);
					}
				}
			}
		}

		// 找到 childrenHereTree.getParentObj(); 为null的就是根 return rootTreeList
		List<UserSubset> rootSubsetList = new ArrayList<UserSubset>();
		for (Iterator<String> it2 = pidSet.iterator(); it2.hasNext();) {
			String key = (String) it2.next();
			List<UserSubset> list = arrMap.get(key);
			for (int i = 0; i < list.size(); i++) {
				UserSubset userSubset = list.get(i);
				if (null == userSubset.getParentObj()) {
					rootSubsetList.add(userSubset);
				}
			}
		}

		return rootSubsetList;

	}

	/**
	 * No.3: 找出 list 中元素的id与pid相同的节点 的并返回.对应关系为: id与父id相同
	 * 
	 * @param list
	 * @param pid
	 * @return
	 */
	public static UserSubset indexOfList(List<UserSubset> list, String pid) {
		for (int i = 0; i < list.size(); i++) {
			UserSubset userSubset = list.get(i);
			/*
			 * pid:是 父ID area_id:是 ID
			 */
			// 这里是我自己定义的变成成,都不一样.所以需要自定义
			if (pid.equals(userSubset.getId())) {
				return userSubset;
			}
		}
		return null;
	}
}
