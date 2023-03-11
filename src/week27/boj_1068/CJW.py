from sys import stdin

N = int(stdin.readline().strip())
arr = list(map(int, stdin.readline().strip().split()))

tree = dict()
reverse_tree = dict()

root = None

for idx, item in enumerate(arr):
    if item not in tree:
        tree[item] = []
    if item == -1:
        root = idx
    tree[item].append(idx)
    reverse_tree[idx] = item

remove = int(stdin.readline().strip())
if root == remove:
    root = None
else:
    if remove in tree:
        tree.pop(remove)

if remove in reverse_tree:
    parent = reverse_tree[remove]
    tree[parent].remove(remove)
    if len(tree[parent]) == 0:
        tree.pop(parent)


def dfs(go):
    result = 0
    if go not in tree:
        return 1

    for item in tree[go]:
        result += dfs(item)

    return result


if root is None:
    print(0)
else:
    print(dfs(root))
