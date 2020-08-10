const TreeUtil = {
  listToTree: (list) => {
    if (!list || !list.length) {
      return [];
    }
    // 按parentId分组（父节点id为空的默认为-1）
    let group = list.reduce((acc, cur) => {
      let parentId = cur.parentId !== null && cur.parentId !== undefined ? cur.parentId : -1;
      if (!acc[parentId]) {
        acc[parentId] = [];
      }
      acc[parentId].push(cur);
      return acc;
    }, {});
    // 设置一级节点
    let tree = group[-1];
    // 遍历节点设置子节点
    let queue = [];
    queue.push(...tree);
    while (queue.length) {
      let node = queue.shift();
      node.children = group[node.id] && group[node.id].length ? group[node.id] : [];
      if (node.children) {
        queue.push(...node.children);
      }
    }
    return tree;
  },
  listSelToTree: (list,selIds) => {
    if (!list || !list.length) {
      return [];
    }
    // 按parentId分组（父节点id为空的默认为-1）
    let group = list.reduce((acc, cur) => {

      if(!selIds.includes(cur.id)){
        return acc;
      }
      let parentId = cur.parentId !== null && cur.parentId !== undefined ? cur.parentId : -1;
      if (!acc[parentId]) {
        acc[parentId] = [];
      }
      acc[parentId].push(cur);
      return acc;
    }, {});
    // 设置一级节点
    let tree = group[-1];
    // 遍历节点设置子节点
    let queue = [];
    queue.push(...tree);
    while (queue.length) {
      let node = queue.shift();
      node.children = group[node.id] && group[node.id].length ? group[node.id] : [];
      if (node.children) {
        queue.push(...node.children);
      }
    }
    return tree;
  }
}
export {TreeUtil}
