package com.visionary_ventures.inventoryvision.ui.theme.screens.library

import type { LucideIcon } from 'lucide-react';
import { LayoutDashboard, UserCircle2, ShoppingBag, Wand2, Palette, Languages, BellDot, Wallet, Settings as SettingsIcon, Database, Lock, Phone } from 'lucide-react'; // Renamed Settings to SettingsIcon to avoid conflict
import type { Product, Kpi, InventoryItem, ReportConfig, SettingsPageConfig } from '@/types';

export const SITE_CONFIG = {
    appName: 'Inventory Vision+',
};

export interface NavItem {
    label: string;
    href: string;
    icon: LucideIcon;
}

export const NAV_LINKS: NavItem[] = [
{ label: 'Wallet', href: '/wallet', icon: Wallet },
{ label: 'Dashboard', href: '/dashboard', icon: LayoutDashboard },
{ label: 'Profile', href: '/profile', icon: UserCircle2 },
{ label: 'Products', href: '/products', icon: ShoppingBag },
{ label: 'Tools', href: '/tools', icon: Wand2 },
{ label: 'Settings', href: '/settings', icon: SettingsIcon },
];

// Mock Data
export const MOCK_KPIS: Kpi[] = [
{ title: 'Total Revenue', value: 'Ksh 4,523,189', change: '+20.1% from last month', changeType: 'positive', icon: 'DollarSign' },
{ title: 'Total Expenses', value: 'Ksh 2,054,312', change: '+10.5% from last month', changeType: 'negative', icon: 'CreditCard' },
{ title: 'Net Profit', value: 'Ksh 2,468,877', change: '+35.2% from last month', changeType: 'positive', icon: 'TrendingUp' },
{ title: 'Active Users', value: '+2350', change: '+180.1% from last month', changeType: 'positive', icon: 'Users' },
];

export const MOCK_PRODUCTS: Product[] = [
{ id: '1', name: 'Premium Widget', category: 'Electronics', price: 9999.00, stock: 150, imageUrl: 'https://placehold.co/100x100.png?text=Widget', description: 'High-quality premium widget.', dataAiHint: 'electronics gadget' },
{ id: '2', name: 'Standard Gadget', category: 'Accessories', price: 4950.00, stock: 300, imageUrl: 'https://placehold.co/100x100.png?text=Gadget', description: 'Reliable standard gadget for daily use.', dataAiHint: 'accessory item' },
{ id: '3', name: 'Eco-Friendly Mug', category: 'Home Goods', price: 1999.00, stock: 50, imageUrl: 'https://placehold.co/100x100.png?text=Mug', description: 'Sustainable and stylish eco-friendly mug.', dataAiHint: 'kitchenware mug' },
{ id: '4', name: 'Smart Thermostat', category: 'Smart Home', price: 12900.00, stock: 80, imageUrl: 'https://placehold.co/100x100.png?text=Thermostat', description: 'Energy-efficient smart thermostat.', dataAiHint: 'home automation' },
];

export const MOCK_INVENTORY: Record<string, InventoryItem[]> = {
    lowStock: [
    { id: 'prod_eco_mug', name: 'Eco-Friendly Mug', stockLevel: 15, reorderPoint: 20, status: 'Low Stock' },
    { id: 'prod_cable_pro', name: 'Pro Charging Cable', stockLevel: 8, reorderPoint: 10, status: 'Low Stock' },
    ],
    overStock: [
    { id: 'prod_basic_pen', name: 'Basic Ballpoint Pen', stockLevel: 1500, optimalLevel: 500, status: 'Overstock' },
    ],
    healthy: [
    { id: 'prod_notebook_a5', name: 'A5 Notebook', stockLevel: 80, reorderPoint: 30, optimalLevel: 100, status: 'Healthy' },
    { id: 'prod_mouse_erg', name: 'Ergonomic Mouse', stockLevel: 55, reorderPoint: 20, optimalLevel: 60, status: 'Healthy' },
    ],
};

export const MOCK_REPORT_CONFIG: ReportConfig = {
    reportType: 'sales_summary',
    schedule: 'weekly',
    deliveryOptions: ['email'],
    lastGenerated: "2024-05-28T11:24:00.000Z",
    customCron: '',
};

export const PROFILE_PREFERENCES = {
    themes: [
    { value: 'light', label: 'Light', icon: Palette },
    { value: 'dark', label: 'Dark', icon: Palette },
    ],
    languages: [
    { value: 'en', label: 'English', icon: Languages },
    { value: 'es', label: 'Español', icon: Languages },
    { value: 'fr', label: 'Français', icon: Languages },
    ],
    notifications: [
    { id: 'newProduct', label: 'New Product Alerts', icon: BellDot },
    { id: 'stockAlerts', label: 'Low Stock Alerts', icon: BellDot },
    { id: 'promotions', label: 'Promotional Offers', icon: BellDot },
    ]
};

export const CONNECTED_SERVICES = [
{ id: 'google', name: 'Google', description: 'Calendar and contacts integration', connected: true, iconName: 'GoogleIcon' },
{ id: 'stripe', name: 'Stripe', description: 'Payment processing', connected: false, iconName: 'StripeIcon' },
{ id: 'slack', name: 'Slack', description: 'Team notifications', connected: true, iconName: 'SlackIcon' },
];

export const MOCK_SETTINGS_PAGE_CONFIG: SettingsPageConfig = {
    userName: "Agenda",
    userStatus: "last seen just now",
    userAvatarUrl: "https://placehold.co/128x128.png",
    userPhoneNumber: "+254 722 947 686",
    settingItems: [
    { id: "notifications", label: "Notifications and Sounds", icon: BellDot },
    { id: "data", label: "Data and Storage", icon: Database },
    { id: "privacy", label: "Privacy and Security", icon: Lock },
    { id: "general", label: "General Settings", icon: SettingsIcon },
    ]
};
