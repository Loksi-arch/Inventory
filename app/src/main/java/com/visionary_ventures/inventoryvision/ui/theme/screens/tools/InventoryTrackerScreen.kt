package com.visionary_ventures.inventoryvision.ui.theme.screens.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useState } from 'react';
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { Badge } from '@/components/ui/badge';
import { MOCK_INVENTORY } from '@/lib/constants';
import type { InventoryItem } from '@/types';
import { ShoppingCart, AlertTriangle, CheckCircle2, PackageX } from 'lucide-react';

@Composable
fun InventoryTracker_Screen(navController: NavHostController) {

    const statusConfig = {
        "Low Stock": { color: "bg-[hsl(var(--highlight-yellow))] text-yellow-900", Icon: AlertTriangle },
        "Overstock": { color: "bg-destructive/80 text-destructive-foreground", Icon: PackageX },
        "Healthy": { color: "bg-emerald-500/80 text-emerald-foreground", Icon: CheckCircle2 },
    };

    interface InventoryListProps {
        items: InventoryItem[];
        category: keyof typeof MOCK_INVENTORY;
    }

    const InventoryList: React.FC<InventoryListProps> = ({ items, category }) => {
        if (items.length === 0) {
            return <p className="text-muted-foreground p-4 text-center">No items in this category.</p>;
        }

        return (
        <div className="border rounded-lg overflow-hidden">
        <Table>
        <TableHeader>
        <TableRow>
        <TableHead>Product Name</TableHead>
        <TableHead className="text-center">Stock Level</TableHead>
        <TableHead className="text-center">Status</TableHead>
        <TableHead className="text-right">Actions</TableHead>
        </TableRow>
        </TableHeader>
        <TableBody>
            {items.map(item => {
                const config = statusConfig[item.status];
                return (
                <TableRow key={item.id}>
                <TableCell className="font-medium">{item.name}</TableCell>
                <TableCell className="text-center">{item.stockLevel}</TableCell>
                <TableCell className="text-center">
                <Badge className={cn("text-xs", config.color)}>
                    {config.Icon && <config.Icon className="mr-1 h-3 w-3" />}
                {item.status}
                </Badge>
                </TableCell>
                <TableCell className="text-right">
                {item.status === 'Low Stock' && (
                    <Button size="sm" variant="outline" className="h-9">
                    <ShoppingCart className="mr-2 h-4 w-4" /> Reorder
                    </Button>
                    )}
                </TableCell>
                </TableRow>
                );
            })}
        </TableBody>
        </Table>
        </div>
        );
    };

    export default function InventoryTracker() {
        const [inventoryData, setInventoryData] = useState(MOCK_INVENTORY);

        return (
        <Tabs defaultValue="lowStock" className="w-full">
        <TabsList className="grid w-full grid-cols-3 mb-4">
        <TabsTrigger value="lowStock">
        <AlertTriangle className="mr-2 h-4 w-4 text-yellow-500" /> Low Stock ({inventoryData.lowStock.length})
        </TabsTrigger>
        <TabsTrigger value="overStock">
        <PackageX className="mr-2 h-4 w-4 text-red-500" /> Overstock ({inventoryData.overStock.length})
        </TabsTrigger>
        <TabsTrigger value="healthy">
        <CheckCircle2 className="mr-2 h-4 w-4 text-emerald-500" /> Healthy ({inventoryData.healthy.length})
        </TabsTrigger>
        </TabsList>
        <TabsContent value="lowStock">
        <InventoryList items={inventoryData.lowStock} category="lowStock" />
        </TabsContent>
        <TabsContent value="overStock">
        <InventoryList items={inventoryData.overStock} category="overStock" />
        </TabsContent>
        <TabsContent value="healthy">
        <InventoryList items={inventoryData.healthy} category="healthy" />
        </TabsContent>
        </Tabs>
        );
    }

    const cn = (...classes: string[]) => classes.filter(Boolean).join(' ');



}

@Preview
@Composable
private fun TrackerPrev() {

    InventoryTracker_Screen(rememberNavController())

}