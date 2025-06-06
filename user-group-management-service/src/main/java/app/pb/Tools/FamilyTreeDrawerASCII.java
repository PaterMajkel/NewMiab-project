package app.pb.Tools;

import java.util.*;
import java.util.stream.Collectors;

import app.pb.DTO.Relationship;
import app.pb.entity.User;

public class FamilyTreeDrawerASCII {

    // Budujemy mapę rodzic -> dzieci
    public static Map<Long, List<Long>> buildParentChildMap(List<Relationship> relationships) {
        return relationships.stream()
                .filter(rel -> Objects.equals(rel.getType(), "FATHER") || Objects.equals(rel.getType(), "Mother"))
                .collect(Collectors.groupingBy(
                        Relationship::getParentId,
                        Collectors.mapping(Relationship::getChildId, Collectors.toList())
                ));
    }

    // Mapa ID -> User
    public static Map<Long, User> buildUserMap(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap(User::getId, u -> u));
    }

    // Rysowanie drzewa ASCII rekurencyjnie - teraz dopisuje do StringBuildera
    private static void appendAsciiTree(Long userId, Map<Long, List<Long>> parentChildMap, Map<Long, User> userMap,
                                        String prefix, boolean isTail, StringBuilder sb) {
        User user = userMap.get(userId);
        if (user == null) {
            sb.append(prefix)
                    .append(isTail ? "└── " : "├── ")
                    .append("UNKNOWN USER (ID=").append(userId).append(")\n");
            return;
        }

        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(user.getUsername())
                .append(" (ID=").append(user.getId()).append(")\n");

        List<Long> children = parentChildMap.get(userId);
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                Long childId = children.get(i);
                appendAsciiTree(childId, parentChildMap, userMap,
                        prefix + (isTail ? "    " : "│   "),
                        i == children.size() - 1, sb);
            }
        }
    }

    // Metoda zwraca gotowy string z całym drzewem ASCII
    public static String drawAsciiFamilyTree(List<User> users, List<Relationship> relationships) {
        Map<Long, List<Long>> parentChildMap = buildParentChildMap(relationships);
        Map<Long, User> userMap = buildUserMap(users);

        Set<Long> allChildren = relationships.stream()
                .filter(r -> Objects.equals(r.getType(), "FATHER") || Objects.equals(r.getType(), "Mother"))
                .map(Relationship::getChildId)
                .collect(Collectors.toSet());

        List<User> roots = users.stream()
                .filter(u -> !allChildren.contains(u.getId()))
                .toList();

        StringBuilder sb = new StringBuilder();

        for (User root : roots) {
            sb.append(root.getUsername())
                    .append(" (ID=").append(root.getId()).append(")\n");

            List<Long> children = parentChildMap.get(root.getId());
            if (children != null) {
                for (int i = 0; i < children.size(); i++) {
                    appendAsciiTree(children.get(i), parentChildMap, userMap, "", i == children.size() - 1, sb);
                }
            }
            sb.append("\n"); // przerwa między drzewami
        }

        return sb.toString();
    }
}
