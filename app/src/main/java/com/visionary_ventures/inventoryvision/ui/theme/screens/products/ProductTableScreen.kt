package com.visionary_ventures.inventoryvision.ui.theme.screens.products

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import Image from 'next/image';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { MoreHorizontal, Edit, Trash2, Eye } from 'lucide-react';
import type { Product } from '@/types';
import { Badge } from '@/components/ui/badge';

@Composable
fun ProductTable_Screen(navController: NavHostController) {

    interface ProductsTableProps {
        products: Product[];
        onEdit: (product: Product) => void;
        onDelete: (productId: string) => void;
    }

    export default function ProductsTable({ products, onEdit, onDelete }: ProductsTableProps) {
        return (
        <div className="rounded-lg border overflow-hidden card-common">
        <Table>
        <TableHeader>
        <TableRow>
        <TableHead className="w-[80px]">Image</TableHead>
        <TableHead>Name</TableHead>
        <TableHead>Category</TableHead>
        <TableHead className="text-right">Price</TableHead>
        <TableHead className="text-right">Stock</TableHead>
        <TableHead className="w-[100px] text-center">Actions</TableHead>
        </TableRow>
        </TableHeader>
        <TableBody>
            {products.length === 0 ? (
                <TableRow>
                <TableCell colSpan={6} className="h-24 text-center">
                No products found.
                </TableCell>
                </TableRow>
                ) : (
                products.map((product) => (
                    <TableRow key={product.id}>
                <TableCell>
                <Image
                src={product.imageUrl || `https://placehold.co/64x64.png?text=${product.name.charAt(0)}`}
                alt={product.name}
                width={48}
                height={48}
                className="rounded-md object-cover"
                data-ai-hint={product.dataAiHint || "product image"}
                />
                </TableCell>
                <TableCell className="font-medium">{product.name}</TableCell>
                <TableCell>
                <Badge variant="secondary">{product.category}</Badge>
                </TableCell>
                <TableCell className="text-right">Ksh {product.price.toFixed(2)}</TableCell>
                <TableCell className="text-right">{product.stock}</TableCell>
                <TableCell className="text-center">
                <DropdownMenu>
                <DropdownMenuTrigger asChild>
                <Button variant="ghost" size="icon" className="h-8 w-8">
                <MoreHorizontal className="h-4 w-4" />
                <span className="sr-only">Product actions</span>
                </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end">
                <DropdownMenuItem onClick={() => { /* Implement view functionality or modal */ }}>
                <Eye className="mr-2 h-4 w-4" /> View
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => onEdit(product)}>
                <Edit className="mr-2 h-4 w-4" /> Edit
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => onDelete(product.id)} className="text-destructive focus:text-destructive focus:bg-destructive/10">
                <Trash2 className="mr-2 h-4 w-4" /> Delete
                </DropdownMenuItem>
                </DropdownMenuContent>
                </DropdownMenu>
                </TableCell>
                </TableRow>
                ))
                )}
        </TableBody>
        </Table>
        </div>
        );
    }

}

@Preview
@Composable
private fun TablePrev() {

    ProductTable_Screen(rememberNavController())

}